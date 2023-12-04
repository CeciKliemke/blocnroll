async function save() {
  let owner = document.getElementById("owner").value;
  let note = document.getElementById("note").value;

  let newNote = {
    notepad: note,
  };

  let options = {
    method: "POST",
    headers: {
      Authorization: "Basic " + btoa("user" + ":" + "123456"),
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newNote),
  };

  console.log(options);
  //id =1;
  let response = await fetch("http://localhost:8080/note/" + id, options);

  if (response.ok) {
    let json = await response.json();
    console.log(json);

    addPostIt();
  } else {
    alert("HTTP-Error: " + response.status);
  }
}

async function find() {
  let owner = document.getElementById("owner").value;
  let note = document.getElementById("note").value;

  let options = {
    method: "GET",
    headers: {
      Authorization: "Basic " + btoa("user" + ":" + "123456"),
      "Content-Type": "application/json",
    },
  };

  console.log(options);
  let response = await fetch("http://localhost:8080/owner/1/notes", options);

  if (response.ok) {
    let json = await response.json();
    console.log(json);

    // Exibir os resultados
    displayResults(json);
  } else {
    alert("HTTP-Error: " + response.status);
  }

  function displayResults(data) {
    let resultsElement = document.getElementById("results");
    resultsElement.innerHTML = ""; // Limpa conteúdo anterior

    if (data && data.length > 0) {
      let resultHTML = "<h3>Results:</h3><ul>";
      data.forEach(item => {
        resultHTML += `<li>${item.notepad}</li>`;
      });
      resultHTML += "</ul>";
      resultsElement.innerHTML = resultHTML;
    } else {
      resultsElement.innerHTML = "<p>No results found.</p>";
    }
  }
}

function addPostIt() {
  const postIt = document.createElement('div');
  postIt.className = 'post-it';
  postIt.style.left = '50px';
  postIt.style.top = '50px';

  const resizeHandle = document.createElement('div');
  resizeHandle.className = 'resize-handle';

  postIt.appendChild(resizeHandle);

  document.body.appendChild(postIt);

  postIt.addEventListener('mousedown', startDragging);
  resizeHandle.addEventListener('mousedown', startResizing);
}

function startDragging(e) {
  const postIt = e.target;
  const offsetX = e.clientX - postIt.getBoundingClientRect().left;
  const offsetY = e.clientY - postIt.getBoundingClientRect().top;

  function dragMove(e) {
    postIt.style.left = e.clientX - offsetX + 'px';
    postIt.style.top = e.clientY - offsetY + 'px';
  }

  function dragEnd() {
    document.removeEventListener('mousemove', dragMove);
    document.removeEventListener('mouseup', dragEnd);
  }

  document.addEventListener('mousemove', dragMove);
  document.addEventListener('mouseup', dragEnd);
}

function startResizing(e) {
  const postIt = e.target.parentElement;
  let isResizing = true;
  let startX = e.clientX;
  let startY = e.clientY;
  let startWidth = postIt.offsetWidth;
  let startHeight = postIt.offsetHeight;

  function resizeMove(e) {
    if (isResizing) {
      const width = startWidth + (e.clientX - startX);
      const height = startHeight + (e.clientY - startY);
      postIt.style.width = width + 'px';
      postIt.style.height = height + 'px';
    }
  }

  function resizeEnd() {
    isResizing = false;
    document.removeEventListener('mousemove', resizeMove);
    document.removeEventListener('mouseup', resizeEnd);
  }

  document.addEventListener('mousemove', resizeMove);
  document.addEventListener('mouseup', resizeEnd);
}