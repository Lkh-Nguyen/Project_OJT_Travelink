document.querySelectorAll('input[name="star"]').forEach(item => {
    item.addEventListener('change', function() {
        filterHotelsByStar(this.value);
    });
});
function filterHotelsByStar(star) {
    var xhr = new XMLHttpRequest();
    xhr.open('GET', 'filterHotelByStarServlet?star=' + star, true);

    xhr.onload = function () {
        if (xhr.status === 200) {
            // Update hotel list container with the response
            document.getElementById('hotelListContainer').innerHTML = xhr.responseText;
        } else {
            console.error('Request failed. Status: ' + xhr.status);
        }
    };

    xhr.send();
}
