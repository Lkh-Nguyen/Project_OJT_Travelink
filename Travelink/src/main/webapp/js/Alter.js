// Giả sử requestScope.updateStatus được định nghĩa và có giá trị là true khi có thông báo
let updateStatus = true; // Giá trị này sẽ được thay đổi dựa trên requestScope.updateStatus

document.addEventListener("DOMContentLoaded", function () {
    if (updateStatus) {
        const statusMessage = document.getElementById("status-message");
        statusMessage.classList.remove('hidden'); // Hiển thị thông báo
        setTimeout(() => {
            statusMessage.style.top = '20px'; // Chuyển động từ trên xuống
        }, 100); // Thời gian trễ ngắn để chắc chắn CSS đã được áp dụng

        // Ẩn thông báo sau 5 giây
        setTimeout(() => {
            statusMessage.style.opacity = '0'; // Làm mờ thông báo
            statusMessage.style.top = '-50px'; // Trả lại vị trí ban đầu
            setTimeout(() => {
                statusMessage.classList.add('hidden'); // Ẩn hoàn toàn sau khi hiệu ứng kết thúc
            }, 500); // Thời gian cho hiệu ứng làm mờ hoàn tất
        }, 4000); // Thời gian hiển thị là 5 giây
    }
});