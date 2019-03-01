$(document).ready(() => {
    var relaties;

    $.ajax('http://localhost:8080/relaties', {'method': 'GET'})
        .done(function (data) {
            relaties = data;

            helpersRelaties.buildDropdown(
                jQuery(data),
                $('.dropdown_relatie'),
                'Relatie'
            );
        })

    $("#createOfferte").on('click', function() {
        let offerte = {
            'offerteId': $("#offertenummer").val(),
            'programmaId': $("#programmanummer").val(),
            'relatieId': $("#relatienummer :selected").val(),
            'deelnemers': $("#aantal-deelnemers").val(),
            'medewerker': $("#medewerker :selected").val(),
            'datum': $("#datum").val(),
            'status': $("#status :selected").val(),

        };
        console.log(JSON.stringify(offerte));

        $.ajax('http://localhost:8080/offerte/', {
            'method': 'POST', contentType: 'application/json', 'data': JSON.stringify(offerte)})
            .done((data) => console.log(data))
            .fail((err) => console.log(err))
    });

    $("#updateOfferte").on('click', function() {
        let offerte = {
            'offerteId': $("#offertenummer").val(),
            'programmaId': $("#programmanummer").val(),
            'relatieId': $("#relatienummer :selected").val(),
            'deelnemers': $("#aantal-deelnemers").val(),
            'medewerker': $("#medewerker :selected").val(),
            'datum': $("#datum").val(),
            'status': $("#status :selected").val(),

        };
        console.log(JSON.stringify(offerte));

        $.ajax('http://localhost:8080/offerte/', {
            'method': 'Put', contentType: 'application/json', 'data': JSON.stringify(offerte)})
            .done((data) => console.log(data))
            .fail((err) => console.log(err))
    });

    $("#updateStatus").on('click', function() {
        let offerte = {
            'offerteId': $("#offertenummer").val(),
            'programmaId': $("#programmanummer").val(),
            'relatieId': $("#relatienummer :selected").val(),
            'deelnemers': $("#aantal-deelnemers").val(),
            'medewerker': $("#medewerker :selected").val(),
            'datum': $("#datum").val(),
            'status': $("#status :selected").val(),

        };
        console.log(JSON.stringify(offerte));

        $.ajax('http://localhost:8080/offerte/', {
            'method': 'Patch', contentType: 'application/json', 'data': JSON.stringify(offerte)})
            .done((data) => console.log(data))
            .fail((err) => console.log(err))
    });
})




var helpersRelaties =
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
                    let h= dropdown.append(`<option value='${el.relatieId} '>${el.naam}</option>`);
                    $(".dropdown").html($(".dropdown").html() + h)

                });
            }
        }
    }



