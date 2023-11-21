<script>
  // Importação do componente Note (caso venha a ser utilizado)
  import Note from './components/Note.svelte';

  // Variáveis de estado
  let notes = []; // Array para armazenar as notas
  let newNoteTitle = ''; // Variável para o título da nova nota
  let newNoteDescription = ''; // Variável para a descrição da nova nota

  // Função para adicionar uma nova nota
  function addNote() {
    // Verifica se o título e a descrição não estão vazios
    if (newNoteTitle.trim() !== '' && newNoteDescription.trim() !== '') {
      // Adiciona a nova nota ao array de notas
      notes = [
        ...notes,
        {
          id: notes.length + 1,
          title: newNoteTitle,
          description: newNoteDescription
        }
      ];
      // Limpa os campos de título e descrição
      newNoteTitle = '';
      newNoteDescription = '';
    }
  }
</script>

<!-- Estrutura principal do aplicativo -->
<main style="background-color: black; color: white; padding: 20px; height: 100vh; display: flex; flex-direction: column; align-items: center;">

  <!-- Caixa para adicionar notas -->
  <div class="add-note-box" style="background-color: white; padding: 20px; width: 300px; box-sizing: border-box; position: fixed; top: 0; right: 0; height: 100%; overflow-y: auto; max-height: calc(100vh - 40px);">
    <!-- Logo acima de "Adicionar Nota" -->
    <div style="text-align: center; margin-bottom: 20px;">
      <img src="logo.png" alt="Logo do Projeto" style="width: 150px; height: auto;">
    </div>

    <!-- Título "Adicionar Nota" -->
    <h2 style="color: black;">Adicionar Nota</h2>

    <!-- Campo para inserir o título da nota -->
    <label style="color: black; margin-top: 10px;">Título:</label>
    <input bind:value={newNoteTitle} style="color: black; background-color: #f5f5f5; border: 1px solid #ccc; padding: 8px; margin-bottom: 10px; border-radius: 4px;" />

    <!-- Campo para inserir a descrição da nota -->
    <label style="color: black;">Descrição:</label>
    <textarea bind:value={newNoteDescription} style="color: black; background-color: #f5f5f5; border: 1px solid #ccc; padding: 8px; margin-bottom: 10px; border-radius: 4px; height: 200px;"></textarea>

    <!-- Botão para adicionar a nota -->
    <button on:click={addNote} style="color: white; background-color: orange; border: none; cursor: pointer; padding: 10px; border-radius: 4px;">Adicionar Nota</button>
  </div>

  <!-- Contêiner para exibir as notas -->
  <div class="notes-container" style="flex: 1; overflow-y: auto; margin-top: 20px; display: flex; flex-wrap: wrap; gap: 10px; justify-content: flex-start;">
    {#each notes as { id, title, description }}
      <!-- Card de nota -->
      <div class="note-card" style="background-color: black; color: white; border: 2px solid orange; border-radius: 4px; width: 250px; height: 250px; box-sizing: border-box; margin-bottom: 10px; overflow: hidden; display: flex; flex-direction: column;">
        <!-- Título da nota -->
        <h3 style="margin-bottom: 10px; overflow: hidden; text-overflow: ellipsis; white-space: nowrap; max-height: 40px;">{title}</h3>
        <!-- Descrição da nota com barra de rolagem -->
        <div style="overflow-y: auto;">
          <p>{description}</p>
        </div>
      </div>
    {/each}
  </div>

</main>

<!-- Estilos CSS -->
<style>
  main {
    margin: 0;
  }

  .notes-container {
    flex: 1;
    overflow-y: auto;
  }

  .add-note-box {
    background-color: white;
    padding: 20px;
    width: 300px;
    box-sizing: border-box;
    position: fixed;
    top: 0;
    right: 0;
    height: 100%;
    overflow-y: auto;
    max-height: calc(100vh - 40px);
  }

  label {
    display: block;
    margin-top: 10px;
  }

  input,
  textarea {
    width: 100%;
    margin-bottom: 10px;
    box-sizing: border-box;
    resize: none;
  }

  button {
    font-size: 18px;
  }

  .note-card {
    background-color: black;
    color: white;
    border: 2px solid orange;
    border-radius: 4px;
    width: 250px;
    height: 250px;
    box-sizing: border-box;
    margin-bottom: 10px;
    overflow: hidden;
    display: flex;
    flex-direction: column;
  }
</style>
