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
      //   Authorization: "Basic ZHZlZ2E6cGFzc3dvcmQ=",
      "Content-Type": "application/json",
    },
    body: JSON.stringify(newNote),
  };

  console.log(options);
  let response = await fetch("http://localhost:8080/note/" + owner, options);

  if (response.ok) {
    let json = await response.json();
    console.log(json);
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
