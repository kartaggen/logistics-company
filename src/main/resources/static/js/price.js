jQuery(document).ready(function ($) {
    let initPrice = 5;
    let initPriceToOffice = 2;
    let pricePerKG = 2;
    $("#admin-db form")
        .on("input", function () {
            if ($.isNumeric($('#inputWeight').val())) {
                let price = $('#inputIsOffice').is(":checked") ? initPriceToOffice : initPrice;
                $('#inputDeliveryPrice').val(price + Math.ceil($('#inputWeight').val() * pricePerKG));
            }
        });
});