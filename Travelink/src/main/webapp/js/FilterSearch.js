function filter(star) {
    let hotels = document.querySelectorAll('.card');
    hotels.forEach(hotel => {
        let hotelStars = hotel.querySelectorAll('.bi-star-fill').length;
        if (hotelStars === star) {
            hotel.style.display = 'block';
        } else {
            hotel.style.display = 'none';
        }
    });
}

function showPage(pageNumber) {
    let pages = document.querySelectorAll('.page');
    pages.forEach((page, index) => {
        if (index === pageNumber - 1) {
            page.style.display = 'block';
        } else {
            page.style.display = 'none';
        }
    });
}
