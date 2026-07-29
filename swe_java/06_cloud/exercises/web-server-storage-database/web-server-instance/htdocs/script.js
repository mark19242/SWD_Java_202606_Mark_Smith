// Javascript code to go along with our pet gallery website

// Fetch legends from the Cloud Function API call
const fetchPetLegends = async () => {
    const response = await fetch(
        'https://us-central1-web-storage-demo.cloudfunctions.net/function-legends',
        {
            method: 'GET',
            headers: {
                accept: 'application/json',
            },
        });
    const listJson = await response.json(); //extract JSON from the http response
    buildLegends(listJson);
}

// Function to add all legends returned from storage
function buildLegends(legendListJson) {
    let table = "";

    // Loop to access all rows 
    for (let legend of legendListJson) {
        table += `
        <tr>
            <td class="image"><img src="https://storage.googleapis.com/web-server-images/images/${legend.imageName}"/></td>
            <td class="legend">${legend.imageLegend}</td>
        </tr>
        `;
    }

    // Setting innerHTML as the built-up table
    document.getElementById("pets").innerHTML = table;
}

// Add all listeners when the document first loads
window.addEventListener('DOMContentLoaded', (event) => {
    // Go get the legends now
    fetchPetLegends();
});

