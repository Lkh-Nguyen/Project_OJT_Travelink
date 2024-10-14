let thisPage = 1;
let limit = 3;
let list = document.querySelectorAll(".list_Card");
let maxPageVisible = 5; // Số trang tối đa hiển thị cùng một lúc

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

    let maxPage = Math.min(count, maxPageVisible);
    let startPage = Math.max(thisPage - Math.floor(maxPageVisible / 2), 1);
    let endPage = startPage + maxPage - 1;

    if (endPage > count) {
        endPage = count;
        startPage = Math.max(endPage - maxPage + 1, 1);
    }

    if (thisPage != 1) {
        let prev = document.createElement('li');
        prev.innerText = 'PREV';
        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
        document.querySelector('.listPage').appendChild(prev);
    }

    for (let i = startPage; i <= endPage; i++) {
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
