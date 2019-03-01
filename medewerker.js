$(document).ready(() => {
    var medewerkers;

    $.ajax('http://localhost:8080/medewerkers', {'method': 'GET'})
        .done(function (data) {
            medewerkers = data;

            helpersMedewerkers.buildDropdown(
                jQuery(data),
                $('.dropdown_medewerker'),
                'Voeg een medewerker toe'
            );
        })










})




var helpersMedewerkers =
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
                    let h= dropdown.append(`<option value='${el.medewerker} '>${el.medewerker}</option>`);
                    $(".dropdown").html($(".dropdown").html() + h)

                });
            }
        }
    }



