const statusMessage = document.getElementById('status-message');
setTimeout(() => {
    statusMessage.classList.add('show'); // Thêm lớp 'show' để hiển thị thông báo
    setTimeout(() => {
        statusMessage.classList.remove('show'); // Loại bỏ lớp 'show' sau 3 giây (3000ms)
    }, 3000); // Bạn có thể thay đổi thời gian tùy theo nhu cầu
}, 100); // Thêm lớp 'show' sau 100ms