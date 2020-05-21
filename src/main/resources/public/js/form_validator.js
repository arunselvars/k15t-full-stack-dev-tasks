$(document).ready(function () {

  $('#name').focusout('input', function () {
    var input = $(this);

    if (/^[a-zA-Z ]+$/.test(input.val())) {
      input.removeClass("invalid").addClass("valid");
    } else {
      alert("Invalid Name, Only alphabets and spaces are allowed");
      input.removeClass("valid").addClass("invalid");
      input.val('');
    }
  });

  $('#registration-form').submit(function () {
    event.preventDefault();
    var data = {}
    data["name"] = $("#name").val();
    data["email"] = $("#email").val();
    data["address"] = $("#address").val();
    data["phone"] = $("#phone").val();
    data["password"] = window.btoa($("#password").val());
    console.log(JSON.stringify(data));
    $.ajax({
      type: "POST",
      url: "/register",
      headers: {
        'USER-REGISTRATION-VERSION': '1.0'
      },
      data: JSON.stringify(data),
      contentType: "application/json; charset=utf-8",
      dataType: "json",
      success: function () {
        alert("You have successfully registered !!");
        $('#registration-form').trigger("reset");
      },
      error: function () {
        alert("You have already registered or something went wrong !! ")
        $('#registration-form').trigger("reset");
      }
    })

  })
});