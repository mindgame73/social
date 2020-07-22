$(document).on('click','.ajax-post-create', function () {
    $('.add-post')[0].reset();
    $('#create-modal-post').modal();
});

$(document).on('click','.ajax-post-edit', function () {
    var id = $(this).parents('.media-body').attr('id');

    $.ajax({
        type: "GET",
        url: "/post/edit",
        data: "id=" + id,
        success: function (post) {
            console.log(post)
            $('.edit-post')[0].reset();
            $('#e_post_id').val(post.postId)
            $('#e_title').val(post.title);
            $('#e_content').val(post.content);
            $('#e_post_time').val(post.postTime);

        }
    });
    $('#edit-modal-post').modal();
});

$('#btn-create-post').click(function () {
    var data = {};
    data['title'] = $('#c_title').val();
    data['content'] = $('#c_content').val();

    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/post/create",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (p) {
            $('#create-modal-post').modal('hide');
            window.location.href = "/index"
        }
    });
});

$('#btn-edit-post').click(function () {
    var data = {};
    data['postId'] = $('#e_post_id').val();
    data['title'] = $('#e_title').val();
    data['content'] = $('#e_content').val();
    data['postTime'] = $('#e_post_time').val();
    console.log(data)
    $.ajax({
        type: "POST",
        contentType: "application/json",
        url: "/post/edit",
        data: JSON.stringify(data),
        dataType: 'json',
        timeout: 600000,
        success: function (d) {
            $('#edit-modal-div').modal('hide');
            location.reload()
        }
    });

});

$('.upvote').click(function () {
    var id = $(this).parents('div').attr('id');

    $.ajax({
        type: "GET",
        url: "/post/upvote",
        data: {post_id : id, upvote: true},
        success: function (p) {
            $('#'+id).children('.upvote').addClass('upvote-on');
        }
    });
});

$('.downvote').click(function () {
    var id = $(this).parents('div').attr('id');

    $.ajax({
        type: "GET",
        url: "/post/upvote",
        data: {post_id : id, upvote: false},
        success: function (p) {
            $('#'+id).children('.downvote').addClass('downvote-on');
        }
    });
});


