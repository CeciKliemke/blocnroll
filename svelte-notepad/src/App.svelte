<script>
  import { onMount } from 'svelte';

  let notes = [];
  let newNoteTitle = '';
  let newNoteDescription = '';
  let draggingNote = null;
  let resizingNote = null;
  let { startX, startY, startWidth, startHeight } = {};

  const scaleFactor = 1.15;

  function addNote() {
    const centerX = window.innerWidth / 2;
    const centerY = window.innerHeight / 2;

    const now = new Date();

    notes = [
      ...notes,
      {
        id: Date.now(),
        x: centerX - 86.25,
        y: centerY - 86.25,
        width: 172.5,
        height: 172.5,
        color: selectedColor,
        title: newNoteTitle,
        description: newNoteDescription,
        createdAt: now.toLocaleString(),
      },
    ];

    newNoteTitle = '';
    newNoteDescription = '';
  }

  let selectedColor = '#87CEEB';

  function handleColorChange(color) {
    selectedColor = color;
  }

  function handleDragStart(event, id) {
    const { clientX, clientY } = event;

    if (event.target.dataset.handle === 'move') {
      draggingNote = id;
      const note = notes.find(note => note.id === draggingNote);
      startX = clientX - note.x;
      startY = clientY - note.y;
    } else if (event.target.dataset.handle === 'resize') {
      resizingNote = id;
      startX = clientX;
      startY = clientY;
      startWidth = notes.find(note => note.id === resizingNote).width;
      startHeight = notes.find(note => note.id === resizingNote).height;
    }
  }

  function handleDrag(event) {
    if (draggingNote !== null) {
      notes = notes.map(note => (note.id === draggingNote ? { ...note, x: event.clientX - startX, y: event.clientY - startY } : note));
    }

    if (resizingNote !== null) {
      const deltaX = event.clientX - startX;
      const deltaY = event.clientY - startY;

      notes = notes.map(note => {
        if (note.id === resizingNote) {
          const newWidth = startWidth + deltaX;
          const newHeight = startHeight + deltaY;
          const newX = newWidth > 0 ? note.x : note.x + newWidth;
          const newY = newHeight > 0 ? note.y : note.y + newHeight;
          return { ...note, x: newX, y: newY, width: newWidth, height: newHeight };
        }
        return note;
      });
    }
  }

  function handleDragEnd() {
    draggingNote = null;
    resizingNote = null;
  }

  function removeNote(id) {
    notes = notes.filter(note => note.id !== id);
  }

  onMount(() => {
    window.addEventListener('mousemove', handleDrag);
    window.addEventListener('mouseup', handleDragEnd);

    return () => {
      window.removeEventListener('mousemove', handleDrag);
      window.removeEventListener('mouseup', handleDragEnd);
    };
  });
</script>

<style>
  main {
    display: flex;
    height: 100vh;
  }

  .sidebar {
    width: 20%;
    padding: 20px;
    background-color: #8C9291;
    color: white;
    box-sizing: border-box;
    overflow-y: auto;
  }

  .content {
    flex-grow: 1;
    position: relative;
    display: flex;
    flex-wrap: wrap;
    background-color: #f0f0f0;
    padding: 20px;
    box-sizing: border-box;
  }

  .note {
    position: absolute;
    background-color: white;
    padding: 15px;
    border: 1px solid #ccc;
    cursor: move;
    display: flex;
    flex-direction: column;
    user-select: none;
    overflow: hidden;
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    margin: 10px;
  }

  .handle {
    position: absolute;
    width: 10px;
    height: 10px;
    cursor: pointer;
  }

  .handle.move {
    top: 0;
    left: 0;
    background-color: #ccc;
  }

  .handle.resize {
    bottom: 0;
    right: 0;
    background-color: #ccc;
  }

  .removeButton {
    position: absolute;
    top: 0;
    right: 0;
    cursor: pointer;
    color: red;
    font-weight: bold;
  }

  .noteTitle {
    font-weight: bold;
    margin-bottom: 8px;
    resize: none;
  }

  .noteDescription {
    flex-grow: 2;
    overflow-y: auto;
    scrollbar-width: thin;
    scrollbar-color: transparent transparent;
    transition: scrollbar-color 0.5s ease;
    margin-bottom: 8px;
    font-weight: 400;
    resize: none;
  }

  .createdAt {
    position: absolute;
    bottom: 5px;
    left: 5px;
    font-size: 12px;
    color: #777;
  }

  .colorPicker {
    display: flex;
    margin-bottom: 10px;
  }

  .colorOption {
    width: 30px;
    height: 30px;
    margin-right: 10px;
    cursor: pointer;
    border: 2px solid transparent;
    border-radius: 50%;
  }

  .colorOption.selected {
    border-color: black;
  }

  .noteText {
    color: #777;
  }

  .grayTextArea {
    width: calc(100% - 20px);
    box-sizing: border-box;
    height: 50px; /* ou 250px para a descrição */
    font-weight: 100;
    resize: none;
    background-color: #f2f2f2; /* Cor cinza */
    border: 1px solid #ccc;
    border-radius: 10px; /* Bordas mais redondas */
    padding: 5px; /* Espaçamento interno */
    margin-bottom: 20px; /* Espaçamento inferior */
  }

  button {
    margin-top: 25px ;
    padding: 10px;
    cursor: pointer;
    background-color: orange; /* Cor laranja */
    color: white;
    border: none;
    border-radius: 5px;
    display: block;
  }
</style>

<main>
  <div class="sidebar">
    <!-- Adiciona a imagem/logo acima da caixa de texto do título -->
    <img src="/logo.png" alt="Logo" style="max-width: 50%; margin: 0 auto 20px; display: block; margin-left: 55px;">

    <textarea
      bind:value={newNoteTitle}
      placeholder="Título"
      class="grayTextArea"
    ></textarea>
    <textarea
      bind:value={newNoteDescription}
      placeholder="Descrição"
      class="grayTextArea"
      style="height: 200px;"
    ></textarea>
    <div class="colorPicker">
      {#each ['#87CEEB', '#C3F8FF', '#FFA07A', '#FFD700', '#98FB98', '#C8A2C8', '#F7969E'] as color}
        <div
          class="colorOption {selectedColor === color ? 'selected' : ''}"
          style="background-color: {color}"
          on:click={() => handleColorChange(color)}
        ></div>
      {/each}
    </div>
    <button on:click={addNote} style="background-color: orange;">Adicionar Nova Anotação</button>
  </div>

  <div class="content">
    {#each notes as { id, x, y, width, height, color, title, description, createdAt }, index (id)}
      <div
        class="note"
        style="transform: translate({x}px, {y}px) scale({scaleFactor}); background-color: {color}; width: {width}px; height: {height}px;"
      >
        <div
          class="handle move"
          data-handle="move"
          on:mousedown={(e) => handleDragStart(e, id)}
        ></div>
        <div
          class="handle resize"
          data-handle="resize"
          on:mousedown={(e) => handleDragStart(e, id)}
        ></div>
        <div
          class="removeButton"
          on:click={() => removeNote(id)}
        >
          X
        </div>
        <div class="noteTitle">{title}</div>
        <div class="noteDescription" style="flex-grow: 2; overflow-y: auto;">{description}</div>
        <div class="createdAt">{createdAt}</div>
      </div>
    {/each}
  </div>
</main>
