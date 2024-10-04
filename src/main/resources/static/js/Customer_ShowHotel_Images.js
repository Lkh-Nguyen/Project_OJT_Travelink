var modal = document.getElementById("myModal");
var modalImg = document.getElementById("img01");
var thumbnails = document.getElementsByClassName("thumbnail");
var currentIndex = 0;

function showModal(index) {
    modal.style.display = "block";
    modalImg.src = thumbnails[index].src;
    currentIndex = index;
}

for (var i = 0; i < thumbnails.length; i++) {
    (function (index) {
        thumbnails[index].onclick = function () {
            showModal(index);
        }
    })(i);
}

var span = document.getElementsByClassName("close")[0];
span.onclick = function () {
    modal.style.display = "none";
}

var prev = document.getElementsByClassName("prev")[0];
var next = document.getElementsByClassName("next")[0];

prev.onclick = function () {
    currentIndex = (currentIndex == 0) ? thumbnails.length - 1 : currentIndex - 1;
    showModal(currentIndex);
}

next.onclick = function () {
    currentIndex = (currentIndex == thumbnails.length - 1) ? 0 : currentIndex + 1;
    showModal(currentIndex);
}