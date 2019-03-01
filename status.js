$(document).ready(() => {
    var status;

    $.ajax('http://localhost:8080/offertestatus', {'method': 'GET'})
        .done(function (data) {
            status = data;

            helpersStatus.buildDropdown(
                jQuery(data),
                $('.dropdown_status'),
                'Kies status'
            );
        })










})




var helpersStatus =
    {
        buildDropdown: function (data, dropdown, emptyMessage) {
// Remove current options
            dropdown.html('');
// Add the empty option with the empty message
            dropdown.append('<option value="">' + emptyMessage + '</option>');
// Check result isnt empty
            if (data != '') {
// Loop through each of the results and append the option to the dropdown
                $.each(data, function (idx, el) {
                    let h= dropdown.append(`<option value='${el.status} '>${el.status}</option>`);
                    $(".dropdown").html($(".dropdown").html() + h)

                });
            }
        }
    }



