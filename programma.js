
$(document).ready( () => {
    var activiteiten;
    var lunch;
    var ontvangst;
    var diner;

    $.ajax('http://localhost:8080/activiteiten/lunch', {'method': 'GET'})
        .done(function (data) {
            lunch = data;

            helpers.buildDropdown(
                jQuery(data),
                $('.lunch_dropdown'),
                'Voeg een lunch toe'
            )
        })
        .fail(function (e) {
            console.log(e)
        });

    $.ajax('http://localhost:8080/activiteiten/ontvangst', {'method': 'GET'})
        .done(function (data) {
            ontvangst = data;

            helpers.buildDropdown(
                jQuery(data),
                $('.ontvangst_dropdown'),
                'Voeg een ontvangst toe'
            )
        })
        .fail(function (e) {
            console.log(e)
        });

    $.ajax('http://localhost:8080/activiteiten/diner', {'method': 'GET'})
        .done(function (data) {
            diner = data;

            helpers.buildDropdown(
                jQuery(data),
                $('.diner_dropdown'),
                'Voeg een diner toe'
            )
        })
        .fail(function (e) {
            console.log(e)
        });

    $.ajax('http://localhost:8080/activiteiten/activiteit', {'method': 'GET'})
        .done(function (data) {
            activiteiten = data;

            helpers.buildDropdown(
                jQuery(data),
                $('.activiteit_dropdown'),
                'Voeg een activiteit toe'
            )
        })
        .fail(function (e) {
            console.log(e)
        })

    var helpers =
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
                        let h = dropdown.append(`<option value='${el.activiteitId} '>${el.naam}</option>`);
                        $(".dropdown").html($(".dropdown").html() + h)

                    });
                }
            }
        };



    $("#activiteitId").on('change', function() {
        let v = $("#activiteitId :selected").val();
        $.each(ontvangst, function(idx, el) {
            if (el.activiteitId == v) {
                console.log(el.tarief);
                $("#prijs-1").val(el.tarief.toFixed(2))
            }
        })
    })

    $("#activiteitId2").on('change', function() {
        let v = $("#activiteitId2 :selected").val();
        $.each(activiteiten, function(idx, el) {
            if (el.activiteitId == v) {
                console.log(el.tarief);
                $("#prijs-2").val(el.tarief.toFixed(2))
            }
        })
    })

    $("#activiteitId3").on('change', function() {
        let v = $("#activiteitId3 :selected").val();
        $.each(lunch, function(idx, el) {
            if (el.activiteitId == v) {
                console.log(el.tarief);
                $("#prijs-3").val(el.tarief.toFixed(2))
            }
        })
    })

    $("#activiteitId4").on('change', function() {
        let v = $("#activiteitId4 :selected").val();
        $.each(activiteiten, function(idx, el) {
            if (el.activiteitId == v) {
                console.log(el.tarief);
                $("#prijs-4").val(el.tarief.toFixed(2))
            }
        })
    })

    $("#activiteitId5").on('change', function() {
        let v = $("#activiteitId5 :selected").val();
        $.each(diner, function(idx, el) {
            if (el.activiteitId == v) {
                console.log(el.tarief);
                $("#prijs-5").val(el.tarief.toFixed(2))
            }
        })
    })


    $("#createAction").on('click', function() {
        let programma = {
            programmaId: $("#programmanummer").val(),
            datum: $("#datum").val(),
            activiteitId: $("#activiteitId :selected").val(),
            activiteitId2: $("#activiteitId2 :selected").val(),
            activiteitId3: $("#activiteitId3 :selected").val(),
            activiteitId4: $("#activiteitId4 :selected").val(),
            activiteitId5: $("#activiteitId5 :selected").val(),
            tijd: $("#tijd-1").val(),
            tijd2: $("#tijd-2").val(),
            tijd3: $("#tijd-3").val(),
            tijd4: $("#tijd-4").val(),
            tijd5: $("#tijd-5").val(),
            offertedId: null
        };
        console.log(JSON.stringify(programma));

        $.ajax('http://localhost:8080/programma/', {
            'method': 'POST', contentType: 'application/json', 'data': JSON.stringify(programma)})
            .done((data) => console.log(data))
            .fail((err) => console.log(err))
    });


});

function succes(){
    alert("Programma is toegevoegd!");
}

function findTotal(){
    var arr = document.getElementsByName('price');
    var tot = 0;

    for(var i=0;i<arr.length;i++){
        if(parseFloat(arr[i].value))
            tot += parseFloat(arr[i].value);
    }

    document.getElementById('total').value = tot.toFixed(2);
}