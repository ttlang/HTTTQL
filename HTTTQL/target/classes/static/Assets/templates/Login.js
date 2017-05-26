  $(function () {
    $('input').iCheck({
      checkboxClass: 'icheckbox_square-blue',
      radioClass: 'iradio_square-blue',
      increaseArea: '20%' // optional
    });
  });
  $(document).ready(function($) {
      $('#login').submit(function(event) {
        /* Act on the event */
          var username = $("#login input[name=username]").val();
          var password = $("#login input[name=password]").val();
        
          if(username == '') {
            $(".login-box-msg").html("Tài khoản không được để trống");
          }else{
             if(password == '' || password.length < 6 ){
              $(".login-box-msg").html('Mật khuẩn bé hỏn 6 kí tự ') ;
            }else{
             
              $.ajax({
                url: '/login',
                type: 'POST',
                data: {username: username ,password:password},
                success : function (response){
                    if(response == 'true'){
                     window.location = "";
                    }else{
                     $(".login-box-msg").html('<spam style="color: red;" >'+response+'</spam>');
                    }
                }
              })
              .done(function() {
                console.log("success");
              })
              .fail(function() {
                console.log("error");
              })
              .always(function() {
                console.log("complete");
              });
              
            }
          }
          return false ;

      }); 
  });
