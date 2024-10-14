/*phan trang*/
let thisPage = 1;
let limit = 4;
let list = document.querySelectorAll(".card-body");
function loadItem() {
    let beginGet = limit * (thisPage - 1);
    let endGet = limit * thisPage - 1;
    list.forEach((item, key) => {
        if (key >= beginGet && key <= endGet) {
            item.style.display = "block";
        } else {
            item.style.display = "none";
        }
    });
    listPage();
}
loadItem();
function listPage() {
    let count = Math.ceil(list.length / limit);
    document.querySelector('.listPage').innerHTML = '';

    if (thisPage != 1) {
        let prev = document.createElement('li');
        prev.innerText = 'PREV';
        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
        document.querySelector('.listPage').appendChild(prev);
    }
    for (i = 1; i <= count; i++) {
        let newPage = document.createElement('li');
        newPage.innerText = i;
        if (i == thisPage) {
            newPage.classList.add('active');
        }
        newPage.setAttribute('onclick', "changePage(" + i + ")");
        document.querySelector('.listPage').appendChild(newPage);
    }

    if (thisPage != count) {
        let next = document.createElement('li');
        next.innerText = 'NEXT';
        next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
        document.querySelector('.listPage').appendChild(next);
    }
}
function changePage(i) {
    thisPage = i;
    loadItem();
    let scrollPosition = window.scrollY;
    window.scrollTo(0, scrollPosition);
}

/*select số lượng phòng nếu lớn hơn 0*/
document.addEventListener('DOMContentLoaded', (event) => {
    const selects = document.querySelectorAll('select');
    const messageCells = document.querySelectorAll('#message');
    const bookingStr = document.getElementById("bookingStr");

    selects.forEach(select => {
        select.addEventListener('change', checkSelectValues);
    });

    function checkSelectValues() {
        let allZero = Array.from(selects).every(select => select.value === '0');
        messageCells.forEach(cell => {
            cell.textContent = allZero ? 'No room selected' : '';
        });
        bookingStr.value = "";
        selects.forEach(select => {

            let row = select.closest('tr');
            let priceCell = row.querySelector('#message');
            let infor = row.querySelector('#infor');
            let basePrice = parseInt(row.getAttribute('data-price'), 10);
            let amount = parseInt(select.value, 10);
            let roomName = row.getAttribute('data-room-name');
            let idHotel = row.getAttribute('data-id-hotel');
            let totalPrice = basePrice * amount;

            if (amount > 0) {
                priceCell.innerHTML = `Room: ${roomName}<br> Quantity: ${amount}<br> Total Price: ${totalPrice.toLocaleString()} VND`;
                infor.value = `Room_ID=${idHotel},Quantity=${amount}`;
                if (bookingStr.value == "") {
                    bookingStr.value = bookingStr.value + infor.value;
                } else {
                    bookingStr.value = bookingStr.value + "/" + infor.value;
                }
            } else {
                priceCell.textContent = 'No room selected';
            }

        });

    }
});
let priceElements = document.querySelectorAll('#price_save');

// Lặp qua tất cả các phần tử và định dạng giá
priceElements.forEach(function (element) {
    let price = parseInt(element.innerText); // Chuyển đổi giá trị text thành số nguyên
    element.innerText = price.toLocaleString('vi-VN') + " VND"; // Định dạng giá và cập nhật lại nội dung
});

function validateForm() {
    var bookingStr = document.getElementById("bookingStr").value;
    if (bookingStr === "") {
        Swal.fire({
            icon: 'error',
            title: 'Oops...',
            text: 'Please choose a room!',
            confirmButtonText: 'OK'
        });
        return false;
    }
    return true;
}


// Get the modal
var modal = document.getElementById("myModal1");

// Get the image and insert it inside the modal
var imgs = document.getElementsByClassName("thumbnail1");
var modalImg = document.getElementById("img01");
for (var i = 0; i < imgs.length; i++) {
    imgs[i].onclick = function () {
        modal.style.display = "block";
        modalImg.src = this.src;
        document.body.style.overflow = "hidden"; // Disable scrolling
    }
}

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close1")[0];

// When the user clicks on <span> (x), close the modal
span.onclick = function () {
    modal.style.display = "none";
    document.body.style.overflow = "auto"; // Enable scrolling
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function (event) {
    if (event.target == modal) {
        modal.style.display = "none";
        document.body.style.overflow = "auto"; // Enable scrolling
    }
}
/*select số lượng phòng nếu lớn hơn 0*/


/* */