function showPacketsRegisteredBy() {
    var id = $('#inputRegisteredBy').val();
    if (id != "") {
        location.href = '/admin/report/registered-by?id=' + id;
    } else {
        alert('Select an employee');
    }
}

function showPacketsSentBy() {
    var id = $('#inputSentBy').val();
    if (id != "") {
        location.href = '/admin/report/sent-by?id=' + id;
    } else {
        alert('Select a client');
    }
}

function showPacketsReceivedBy() {
    var id = $('#inputReceivedBy').val();
    if (id != "") {
        location.href = '/admin/report/received-by?id=' + id;
    } else {
        alert('Select a client');
    }
}

function showIncome() {
    var inputDateFrom = $('#inputDateFrom').val();
    var inputDateThrough = $('#inputDateThrough').val();
    if (isValidDate(inputDateFrom) && isValidDate(inputDateThrough)) {
        var dateFrom = new Date($('#inputDateFrom').val());
        var dateThrough = new Date($('#inputDateThrough').val());
        if (dateFrom.getTime() < dateThrough.getTime()) {
            location.href = '/admin/report/income?dateFrom=' + dateFrom.getTime() + '&dateThrough=' + dateThrough.getTime();
        } else {
            alert('"From" needs to be before "Through"');
        }
    } else {
        alert('Invalid "From" and/or "Through" dates');
    }
}

function isValidDate(dateString) {
    var regEx = /^\d{4}-\d{2}-\d{2}$/;
    if (!dateString.match(regEx)) return false;
    var d = new Date(dateString);
    var dNum = d.getTime();
    if (!dNum && dNum !== 0) return false;
    return d.toISOString().slice(0, 10) === dateString;
}