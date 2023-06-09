window.mainMapView = null;
console.log('Hello from Ivan')

window.createMapView = function createMapView(id) {
    if (mainMapView) {
        console.log("first map value ", mainMapView)
        return;
    }
    try {
        var element = document.getElementById(`${id}`)
        console.log("element finded ", element)
        mainMapView = L.map(element)
            .setView([51.505, -0.09], 13);
        console.log("map created ", mainMapView)

        var tile = L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: 'Map data &copy; OpenStreetMap contributors'
        }).addTo(mainMapView);
        console.log("new tile added ", tile)
    } catch (e) {
        console.log("exception from me ", e)
    } finally {
        console.log("finally")
    }
}

window.addRoute = function addRoute(pathCoords, color) {
    console.log("path coords is ", pathCoords);
    L.polyline(pathCoords, {
        color: color,
        weight: 7,
        opacity: 1
    }).addTo(mainMapView);
    console.log("Polyline with coords added to map");
    const coordinate = pathCoords[0];
    console.log("start coordinate is ", coordinate);
    if(coordinate && mainMapView){
        mainMapView.setView(coordinate, 10)
    }
}



