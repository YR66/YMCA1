$('#read').click(function () {
    $.ajax('/activiteit?activiteitId='+ $('#activiteitId').val(), {
        data: { id: $('#activiteitId').val() }
    }).done(function(json) {
       // $('#activiteitId').val(json.activiteitId);
        $('#naam').val(json[0].naam);
        $('#omschrijving').val(json[0].omschrijving);
        $('#tarief').val(json[0].tarief);
        $('#tijdsduur').val(json[0].tijdsduur);
        $('#activiteitSoort').val(json[0].activiteitSoort);
        $('#aanvang').val(json[0].aanvang);
    });
});


$('#create').click(function() {
    $.ajax('/activiteit', {
        data: {
            activiteitId: $('#activiteitId').val(),
            naam: $('#naam').val(),
            omschrijving: $('#omschrijving').val(),
            tarief: $('#tarief').val(),
            tijdsduur: $('#tijdsduur').val(),
            activiteitSoort: $('#activiteitSoort').val(),
            // aanvang: $('#tijd').val(),

        },
        method: 'post'
    }).done(function(json) {
        $('#activiteitId').val(json.activiteitId);
        $('#naam').val(json.naam);
        $('#omschrijving').val(json.omschrijving);
        $('#tarief').val(json.tarief);
        $('#tijdsduur').val(json.tijdsduur);
        $('#activiteitSoort').val(json.activiteitSoort);
        // $('#aanvang').val(json.aanvang);
    });
});

$('#update').click(function() {
    $.ajax('/activiteit?activiteitId='+ $('#activiteitId').val(), {
        data: {
            activiteitId: $('#activiteitId').val(),
            naam: $('#naam').val(),
            omschrijving: $('#omschrijving').val(),
            tarief: $('#tarief').val(),
            tijdsduur: $('#tijdsduur').val(),
            activiteitSoort: $('#activiteitSoort').val(),
            // aanvang: $('#tijd').val(),
        },
        method: 'put'
    }).done(function(json) {
        $('#activiteitId').val(json.activiteitId);
        $('#naam').val(json.naam);
        $('#omschrijving').val(json.omschrijving);
        $('#tarief').val(json.tarief);
        $('#tijdsduur').val(json.tijdsduur);
        $('#activiteitSoort').val(json.activiteitSoort);
        // $('#aanvang').val(json.aanvang);
    });
});
$('#delete').click(function() {
    $.ajax('/activiteit?activiteitId='+ $('#activiteitId').val(), {
        data: { id: $('#activiteitId').val() },
        method: 'delete'
    }).done(function(json) {
        $('#activiteitId').val(json.activiteitId);
        $('#naam').val(json.naam);
        $('#omschrijving').val(json.omschrijving);
        $('#tarief').val(json.tarief);
        $('#tijdsduur').val(json.tijdsduur);
        $('#activiteitSoort').val(json.activiteitSoort);
        // $('#aanvang').val(json.aanvang);
    });
});

