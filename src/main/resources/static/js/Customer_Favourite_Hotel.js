    // Lắng nghe sự kiện nhấn nút "Xóa"
    document.getElementById('confirmDeleteButton').addEventListener('click', function () {
        // Gửi form xóa
        document.getElementById('deleteForm').submit();
    });