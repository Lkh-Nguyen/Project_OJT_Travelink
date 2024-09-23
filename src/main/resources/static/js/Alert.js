$(document).ready(function() {
    // Hiện thông báo nếu có lỗi
    if ($('#status-message').length) {
        $('#status-message').addClass('show').css('opacity', '1'); // Hiển thị thông báo
        setTimeout(function() {
            $('#status-message').removeClass('show').css('opacity', '0'); // Ẩn thông báo
        }, 3000); // Thời gian hiển thị 5000 ms
    }
});

