// Javascript code to go along with our pet gallery website

// Fetch legends from the Cloud Function API call
// const fetchPetLegends = async () => {
//     const response = await fetch(
//         'https://us-central1-web-storage-demo.cloudfunctions.net/function-legends',
//         {
//             method: 'GET',
//             headers: {
//                 accept: 'application/json',
//             },
//         });
//     const listJson = await response.json(); //extract JSON from the http response
//     buildLegends(listJson);
// }

// Function to add all legends returned from storage
function buildLegends(petList) {
    let gallery = "";

    for (const pet of petList) {
        gallery += `
            <article class="pet-card">
                <h2>${pet.petName}</h2>
                <p>${pet.imageLegend}</p>
                <img
                    src="images/${pet.imageName}"
                    alt="${pet.petName}: ${pet.imageLegend}">
            </article>
        `;
    }

    document.getElementById("pets").innerHTML = gallery;
}

// Add all listeners when the document first loads
window.addEventListener('DOMContentLoaded', () => {
    const localPetLegends = [
        {
            petName: "Milo",
            imageName: "cat.png",
            imageLegend: "A friendly and curious cat"
        },
        {
            petName: "Buddy",
            imageName: "dog.png",
            imageLegend: "A playful and loyal dog"
        },
        {
            petName: "Shelly",
            imageName: "turtle.png",
            imageLegend: "A calm and patient turtle"
        },
        {
            petName: "Hopper",
            imageName: "rabbit.png",
            imageLegend: "A quick and energetic rabbit"
        },
        {
            petName: "Nibbles",
            imageName: "hamster.png",
            imageLegend: "A tiny hamster who loves snacks"
        },
        {
            petName: "Rio",
            imageName: "parrot.png",
            imageLegend: "A colorful and talkative parrot"
        },
        {
            petName: "Bubbles",
            imageName: "goldfish.png",
            imageLegend: "A peaceful little goldfish"
        },
        {
            petName: "Peanut",
            imageName: "guinea-pig.png",
            imageLegend: "A gentle and social guinea pig"
        },
        {
            petName: "Bandit",
            imageName: "ferret.png",
            imageLegend: "A clever and adventurous ferret"
        },
        {
            petName: "Spike",
            imageName: "lizard.png",
            imageLegend: "A relaxed and observant lizard"
        },
        {
            petName: "Jumper",
            imageName: "frog.png",
            imageLegend: "A lively frog who loves water"
        },
        {
            petName: "Poppy",
            imageName: "hedgehog.png",
            imageLegend: "A shy but lovable hedgehog"
        }
    ];

    buildLegends(localPetLegends);
});

