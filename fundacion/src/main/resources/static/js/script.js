// searchTable.js

console.log("El archivo searchTable.js se cargó correctamente.");

function searchTable() {
    var input, filter, table, tr, td, i, txtValue;
    input = document.getElementById("searchInput");
    filter = input.value.toUpperCase();
    table = document.getElementById("dataTable");
    tr = table.getElementsByTagName("tr");
    for (i = 0; i < tr.length; i++) {
        td = tr[i].getElementsByTagName("td")[0]; // Cambia el número para buscar en diferentes columnas
        if (td) {
            txtValue = td.textContent || td.innerText;
            if (txtValue.toUpperCase().indexOf(filter) > -1) {
                tr[i].style.display = "";
            } else {
                tr[i].style.display = "none";
            }
        }
    }
    
    // Verificar si hay resultados de búsqueda
    var tableRows = table.getElementsByTagName("tr");
    var visibleRows = 0;
    for (var j = 0; j < tableRows.length; j++) {
        if (tableRows[j].style.display !== "none") {
            visibleRows++;
        }
    }
    // Ocultar la tabla si no hay resultados
    if (visibleRows === 0) {
        table.style.display = "none";
    } else {
        table.style.display = ""; // Mostrar la tabla si hay resultados
    }
}