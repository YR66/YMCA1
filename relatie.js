$('#read').click(function () {
    $.ajax('/relatie?relatieId='+ $('#relatieId').val(), {
        data: { id: $('#relatieId').val() }
    }).done(function(json) {
       // $('#relatieId').val(json.relatieId);
        $('#naam').val(json.naam);
        $('#adres').val(json.adres);
        $('#postcode').val(json.postcode);
        $('#woonplaats').val(json.woonplaats);
        $('#email').val(json.email);
        $('#organisatie').val(json.organisatie);
    });
});


$('#create').click(function() {
    $.ajax('/relatie', {
        data: {
            naam: $('#naam').val(),
            adres: $('#adres').val(),
            postcode: $('#postcode').val(),
            woonplaats: $('#woonplaats').val(),
            email: $('#email').val(),
            telefoonnummer: $('#telefoonnummer').val(),
            organisatie:$('#organisatie').val(),
        },
        method: 'post'
    }).done(function(json) {
        $('#relatieId').val(json.relatieId);
        $('#naam').val(json.naam);
        $('#adres').val(json.adres);
        $('#postcode').val(json.postcode);
        $('#woonplaats').val(json.woonplaats);
        $('#telefoonnummer').val(json.telefoonnummer);
        $('#email').val(json.email);
        $('#organisatie').val(json.organisatie);
    });
});

$('#update').click(function() {
    $.ajax('/relatie?relatieId='+ $('#relatieId').val(), {
        data: {
            relatieId: $('#relatieId').val(),
            naam: $('#naam').val(),
            adres: $('#adres').val(),
            postcode: $('#postcode').val(),
            woonplaats: $('#woonplaats').val(),
            telefoonnummer: $('#telefoonnummer').val(),
            email: $('#email').val(),
            organisatie:$('#organisatie').val(),
        },
        method: 'put'
    }).done(function(json) {
        $('#relatieId').val(json.relatieId);
        $('#naam').val(json.naam);
        $('#adres').val(json.adres);
        $('#postcode').val(json.postcode);
        $('#woonplaats').val(json.woonplaats);
        $('#telefoonnummer').val(json.telefoonnummer);
        $('#email').val(json.email);
        $('#organisatie').val(json.organisatie);
    });
});
$('#delete').click(function() {
    $.ajax('/relatie?relatieId='+ $('#relatieId').val(), {
        data: { id: $('#relatieId').val() },
        method: 'delete'
    }).done(function(json) {
        $('#relatieId').val(json.relatieId);
        $('#naam').val(json.naam);
        $('#adres').val(json.adres);
        $('#postcode').val(json.postcode);
        $('#woonplaats').val(json.woonplaats);
        $('#telefoonnummer').val(json.telefoonnummer);
        $('#email').val(json.email);
        $('#organisatie').val(json.organisatie);
    });
});

