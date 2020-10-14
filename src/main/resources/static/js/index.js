jQuery(document).ready(function ($) {
    $('#trackBtn').on('click', function () {
        let trackNum = $('#trackNum').val();
        if (trackNum != "") {
            location.href = '/tracking?trackNumber=' + trackNum;
        }
    });
});