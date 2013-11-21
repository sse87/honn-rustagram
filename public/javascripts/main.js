
$(document).ready(function () {

    console.log("Document ready!");

    $('input#addComment').keyup(function (e) {

        if (e.which == 13) {
            console.log("RETURN pressed!");

            var sessionUsername = $('#sessionUsername').val();
            var imageId = $('#imageId').val();
            var comment = $(this).val();

            console.log('imageId: ' + imageId + ' - comment: ' + comment);

            $.ajax({
                type: "POST",
                url: "/comment",
                data: { sessionUsername: sessionUsername, imageId: imageId, comment: comment },
                dataType: "json"
            }).done(function (json) {

                console.log("ajax returned!");
                console.log(json);

            });


            $('.comments').append('<div class="comment"><div class="commenter">' + sessionUsername + '</div><div class="theComment">' + comment + '</div></div>');
            $(this).val("");
            console.log("ajax sent!");

        }

    });




});

console.log("FIRST!");












