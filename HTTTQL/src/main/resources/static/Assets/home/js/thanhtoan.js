$("#cart-client").on('click', '.delete-cart', function (event) {
    $.ajax({
        url: '/Order/clear',
        type: 'GET',
        success: function () {
            displayCart();
            cartClient();
        }
    })
       .done(function () {
           console.log("success");
       })
       .fail(function () {
           console.log("error");
       })
       .always(function () {
           console.log("complete");
       });

  
});
$("#cart-client").on("click", ".delete-item", function (event) {
    var name = $(this).attr("data-name");
    var id = $(this).attr("data-id");
    //                 alert(id);

    $.ajax({
        url: '/Order/removeItemFromCart',
        type: 'GET',
        data: { product_id: id },
        success: function (res) {
            if ($.trim(res) == 'True') {

                displayCart();
                cartClient();
            } else {
                alert("Xoa that bai");

            }
        }
    })
         .done(function () {
             console.log("success");
         })
         .fail(function () {
             console.log("error");
         })
         .always(function () {
             console.log("complete");
         });


});


$("#cart-client").on("click", ".subtract-item", function (event) {
    var name = $(this).attr("data-name");
    var id = $(this).attr("data-id");
    //                 alert(id);
    var num = parseInt($(this).parents(".clearfix").find('.item-count').val());
    num--;
    if (num <= 0) {
        alert("không thê giam số luong ");
    } else {
        $.ajax({
            url: '/Order/addItemToCart',
            type: 'GET',
            data: { product_id: id, count: num },
            success: function (res) {

                if ($.trim(res) == 'True') {

                    displayCart();
                    cartClient();
                } else {
                    alert("them k thanh cong");
                }
            }
        })
.done(function () {
    console.log("success");
})
.fail(function () {
    console.log("error");
})
.always(function () {
    console.log("complete");
});
    }


  
    displayCart();
    cartClient();
});

$("#cart-client").on("click", ".plus-item", function (event) {
    var name = $(this).attr("data-name");
    var id = $(this).attr("data-id");
    //                 alert(id);
   
    var num = parseInt($(this).parents(".clearfix").find('.item-count').val());
    num++;
    if (num >= 10) {
        alert("không thê tang số luong ");
    } else {

        $.ajax({
            url: '/Order/addItemToCart',
            type: 'GET',
            data: { product_id: id, count: num },
            success: function (res) {

                if ($.trim(res) == 'True') {

                    displayCart();
                    cartClient();

                } else {
                    alert("them k thanh cong");
                }
            }
        })
.done(function () {
    console.log("success");
})
.fail(function () {
    console.log("error");
})
.always(function () {
    console.log("complete");
});


    }

   
});

$("#cart-client").on("change", ".item-count", function (event) {
    var name = $(this).attr("data-name");
    var count = Number($(this).val());
    var id = $(this).attr("data-id");
   
    var num = Number($(this).val());
    $.ajax({
        url: '/Order/addItemToCart',
        type: 'GET',
        data: { product_id: id, count: num },
        success: function (res) {

            if ($.trim(res) == 'True') {

                displayCart();
                cartClient();
            } else {
                alert("them k thanh cong");
            }
        }
    })
   .done(function () {
       console.log("success");
   })
   .fail(function () {
       console.log("error");
   })
   .always(function () {
       console.log("complete");
   });

   
});


displayCart();
cartClient();

function cartClient() {
    var data = $.getValuesPost("/Order/orderCart");
    var cartArray = data.data;

    //                  <tr>
    //      <td data-column="Hình ảnh">
    //          <img src="images/300x300.png" class="productimg" width="50" height="50"></td>
    //      <td data-column=""> Tên dài thiêt là dài qua dài lun</td>
    //      <td data-column="Số lượng"><span class="item-price">2 x 6 = 34000.00</span><button id="button" class="plus-item" data-name="one1">+</button> <button class="subtract-item" id="button" data-name="one1">-</button> <span class="quantity"> <input class="item-count" type="number" data-name="one1" value="5"> </span> </td>
    //      <td data-column="Loại bỏ"> <button class="delete-item" id="button" data-name="one1">X</button></td>
    //    </tr>

    var output = '';
    var countCart = $.getValuesGet("/Order/countCart");
    var totalCart = $.getValuesGet("/Order/totalCostInCart");
    for (var i in cartArray) {
        output += "<tr >"
         + " <td data-column='Hình ảnh'><img  src='" + cartArray[i].img + "' class='productimg' width='50' height='50' > </td> <td data-column=''>" + cartArray[i].name + "</td>"
         + " <td data-column='Số lượng'><span class='item-price'>" + cartArray[i].price + "</span>"
         + "<button data-id='" + cartArray[i].product_id + "' id='button' class='plus-item' data-name='"
            + cartArray[i].name + "'>+</button>"
            + " <button class='subtract-item' id='button' data-id='" + cartArray[i].product_id + "' data-name='"
            + cartArray[i].name + "'>-</button>"
            + "<span class='quantity'> <input class='item-count' type='number' data-id='" + cartArray[i].product_id + "' data-name='"
            + cartArray[i].name + "' value='" + cartArray[i].count + "'> </span> </td> "
            + "<td>  <button class='delete-item' id='button' data-id='" + cartArray[i].product_id + "' data-name='"
            + cartArray[i].name + "'>X</button>  </td></tr>  ";
    }
    output += "<tr > <td data-column='Tổng số lượng'>Tổng Số lượng : " + countCart + "</td> "
            + " <td data-column='Tổng tiền'> Tổng tiền : " + totalCart + "</td>"
            + "<td > <a  class='delete-cart' href='#' >Xóa giỏ</a></td></tr>";

    $("#cart-client").html(output);
    //                   $(".count-cart").html( shoppingCart.countCart() );
    //                      $("#total-cart").html( shoppingCart.totalCart() );
    //                     console.log(shoppingCart.listCart());
}