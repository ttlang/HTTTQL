                 $('.crsl-item .add-to-cart').on('click',function(){
                    // hieu ung mua hàng 
                var itemImg = $(this).parent().find('img').eq(0);
                   flyToElement($(itemImg), $('.cart_anchor'));
                   // end hieu ung
                   // chuan bi them san pham 
                    var root = $(this).parents(".crsl-item");
                        var images = root.find(".thumbnail").find("img").attr("src");
                          var price = root.find(".price").find(".new").text().replace(/,/g,"");
                          var id = $(this).attr("data-id") ;
                          alert(id);
                         var name =root.find(".postdate").text();
//                           shoppingCart.clearCart();
                                $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=addItemToCart&&id="+id+"&&qty=1",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                         shoppingCart.addItemToCart(name, price,images, 1 , id);
                       
                  displayCart(); 
                  cartClient();
                  });    
                    $("#clear-cart").click(function(event){
                        $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=clearCart",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                        shoppingCart.clearCart();
                        
                           displayCart();
                           cartClient() ;
                         });
                    $("#notificationsBody").on("click", ".delete-item", function(event){
                var name = $(this).attr("data-name");
                 var id = $(this).attr("data-id");
                 alert(id);
                 
                  $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=removeItemFromCartAll&&id="+id+"",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                shoppingCart.removeItemFromCartAll(id);
                displayCart();
                cartClient() ;
            });

            $("#notificationsBody").on("click", ".subtract-item", function(event){
                var name = $(this).attr("data-name");
                 var id = $(this).attr("data-id");
                 alert(id);
                  $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=removeItemFromCart&&id="+id+"",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
  
                shoppingCart.removeItemFromCart(id);
                displayCart();
                cartClient() ;
                
            });
            $("#notificationsBody").on("click", ".plus-item", function(event){
                var name = $(this).attr("data-name");
                var id = $(this).attr("data-id");
                 alert(id);
                     $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=addItemToCart&&id="+id+"&&qty=1",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                shoppingCart.addItemToCart(name, 0,"", 1 , id);
                displayCart();
                cartClient() ;
            });

            $("#notificationsBody").on("change", ".item-count", function(event){
                var name = $(this).attr("data-name");
                var count = Number($(this).val());
                 var id = $(this).attr("data-id");
                 alert(id);
                  $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=setCountForItem&&id="+id+"&&qty="+count+"",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                shoppingCart.setCountForItem( id , count);
                displayCart();
                cartClient() ;
            });

            
            
            
            
             $("#cart-client").on('click','.delete-cart',function(event){
                          $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=clearCart",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                        shoppingCart.clearCart();
                           displayCart();
                           cartClient();
                         });
               $("#cart-client").on("click", ".delete-item", function(event){
                      var name = $(this).attr("data-name");
                 var id = $(this).attr("data-id");
                 alert(id);
                 
                  $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=removeItemFromCartAll&&id="+id+"",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                shoppingCart.removeItemFromCartAll(id);
                displayCart();
                cartClient() ;
               
            });

            $("#cart-client").on("click", ".subtract-item", function(event){
            var name = $(this).attr("data-name");
                 var id = $(this).attr("data-id");
                 alert(id);
                  $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=removeItemFromCart&&id="+id+"",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
  
                shoppingCart.removeItemFromCart(id);
                displayCart();
                cartClient() ;
            });

            $("#cart-client").on("click", ".plus-item", function(event){
               var name = $(this).attr("data-name");
                var id = $(this).attr("data-id");
                 alert(id);
                     $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=addItemToCart&&id="+id+"&&qty=1",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                shoppingCart.addItemToCart(name, 0,"", 1 , id);
                displayCart();
                cartClient() ;
            });

            $("#cart-client").on("change", ".item-count", function(event){
                var name = $(this).attr("data-name");
                var count = Number($(this).val());
                 var id = $(this).attr("data-id");
                 alert(id);
                  $.ajax({type: 'POST' ,
                                 url: "Shoppingcart?action=setCountForItem&&id="+id+"&&qty="+count+"",
                                 success: function (data, textStatus, jqXHR) {
                                         alert(data);
                                     }
                                  });
                shoppingCart.setCountForItem( id , count);
                displayCart();
                cartClient() ;
            });


            displayCart();
            cartClient();
            
                 function  cartClient(){
                       var cartArray = shoppingCart.listCart();
                 
//                  <tr>
//      <td data-column="Hình ảnh">
//          <img src="images/300x300.png" class="productimg" width="50" height="50"></td>
//      <td data-column=""> Tên dài thiêt là dài qua dài lun</td>
//      <td data-column="Số lượng"><span class="item-price">2 x 6 = 34000.00</span><button id="button" class="plus-item" data-name="one1">+</button> <button class="subtract-item" id="button" data-name="one1">-</button> <span class="quantity"> <input class="item-count" type="number" data-name="one1" value="5"> </span> </td>
//      <td data-column="Loại bỏ"> <button class="delete-item" id="button" data-name="one1">X</button></td>
//    </tr>
                 
                       var output = '';

                           for (var i in cartArray) {
                    output +=  "<tr >"
                     +" <td data-column='Hình ảnh'><img  src='"+cartArray[i].images+"' class='productimg' width='50' height='50' > </td> <td data-column=''>"+cartArray[i].name+"</td>"
                     +" <td data-column='Số lượng'><span class='item-price'>" +cartArray[i].total+"</span>"
                     + "<button data-id='" +cartArray[i].id+"' id='button' class='plus-item' data-name='"
                        +cartArray[i].name+"'>+</button>"
                        +" <button class='subtract-item' id='button' data-id='" +cartArray[i].id+"' data-name='"
                        +cartArray[i].name+"'>-</button>"
                        +"<span class='quantity'> <input class='item-count' type='number' data-id='" +cartArray[i].id+"' data-name='"
                        +cartArray[i].name+"' value='"+cartArray[i].count+"'> </span> </td> "
                        + "<td>  <button class='delete-item' id='button' data-id='" +cartArray[i].id+"' data-name='"
                        +cartArray[i].name+"'>X</button>  </td></tr>  " ;
                     }
                     output += "<tr > <td data-column='Tổng số lượng'>Tổng Số lượng : "+shoppingCart.countCart()+"</td> "
                             + " <td data-column='Tổng tiền'> Tổng tiền : "+shoppingCart.totalCart()+"</td>" 
                             + "<td > <a  class='delete-cart' href='#' >Xóa giỏ</a></td></tr>";
                     
                        $("#cart-client").html(output);
//                   $(".count-cart").html( shoppingCart.countCart() );
//                      $("#total-cart").html( shoppingCart.totalCart() );
//                     console.log(shoppingCart.listCart());
                  } 
                  
                  // hien thi gio hang
                  
                  
                  
                  
                function  displayCart(){
                       var cartArray = shoppingCart.listCart();
                       if(cartArray.length !== 0){
                           $("#thanhtoan").show();
                       }else{
                             $("#thanhtoan").hide();
                       }
                       var output = '';
//                        <div class="clearfix"><img src="images/300x300.png" class="productimg">
//                                 <h4>Purple Jeggings </h4>
//                                <span class="item-price">10.910.000.000</span>
//                                <button id="button" class="plus-item" data-name="Purple Jeggings">+</button>
//                                <button class="subtract-item" id="button" data-name="Purple Jeggings">-</button> 
//                                   <button class="delete-item" id="button" data-name="Purple Jeggings">X</button> 
//                            <span class="quantity"> <input class="item-count" type="number" data-name="Purple Jeggings" value="7"> </span>
//                            </div> 
                           for (var i in cartArray) {
                    output +=  "<div class='clearfix'>"
                     +"<img  src='"+cartArray[i].images+"' class='productimg' width='50' height='50' ><h4>"+cartArray[i].name+"</h4>"
                     +"<span class='item-price'>" +cartArray[i].total+"</span>"
                     + "<button data-id='" +cartArray[i].id+"' id='button' class='plus-item' data-name='"
                        +cartArray[i].name+"'>+</button>"
                        +" <button data-id='" +cartArray[i].id+"' class='subtract-item' id='button' data-name='"
                        +cartArray[i].name+"'>-</button>"
                        +" <button class='delete-item' id='button' data-id='" +cartArray[i].id+"' data-name='"
                        +cartArray[i].name+"'>X</button> <span class='quantity'> <input class='item-count' type='number' data-id='" +cartArray[i].id+"'  data-name='"
                        +cartArray[i].name+"' value='"+cartArray[i].count+"'> </span> </div>  " ;
                }      
                        $("#notificationsBody").html(output);
                   $(".count-cart").html( shoppingCart.countCart() );
                      $("#total-cart").html( shoppingCart.totalCart() );
                     console.log(shoppingCart.listCart());
                  }
                  
                  
                  
                     var shoppingCart = (function () {
    // Private methods and properties
    var  clientcart = [];

    function clientItem(name, price,images, count , id) {
        this.name = name ;
        this.price = price ;
        this.count = count;
        this.images = images ;
        this.id = id ;
    }

    function saveCart() {
        localStorage.setItem("clientcart", JSON.stringify(clientcart));
    }

    function loadCart() {
        clientcart = JSON.parse(localStorage.getItem("clientcart"));
        if (clientcart === null) {
            clientcart = []
        }
    }
    loadCart();
    // Public methods and properties
    var obj = {};
 obj.addItemToCart = function (name, price,images , count , id ) {
        for (var i in clientcart) {
            if (clientcart[i].id === id) {
                clientcart[i].count += count;
                saveCart();
                return;
            }
        }

        console.log("addItemToCart:", name, price, count);

        var item = new clientItem(name, price,images , count , id);
        clientcart.push(item);
        saveCart();
        
    };

    obj.setCountForItem = function ( id , count) {
        for (var i in clientcart) {
            if (clientcart[i].id === id) {
                clientcart[i].count = count;
                break;
            }
        }
        saveCart();
    };


    obj.removeItemFromCart = function ( id ) { // Removes one item
        for (var i in clientcart) {
            if (clientcart[i].id === id) { // "3" === 3 false
                clientcart[i].count--; // cart[i].count --
                if (clientcart[i].count === 0) {
                    clientcart.splice(i, 1);
                }
                break;
            }
        }
        saveCart();
    };


    obj.removeItemFromCartAll = function (id) { // removes all item name
        for (var i in clientcart) {
            if (clientcart[i].id === id) {
                clientcart.splice(i, 1);
                break;
            }
        }
        saveCart();
    };


    obj.clearCart = function () {
        clientcart = [];
        saveCart();
    }


    obj.countCart = function () { // -> return total count
        var totalCount = 0;
        for (var i in clientcart) {
            totalCount += clientcart[i].count;
        }
        return totalCount;
    };

    obj.totalCart = function () { // -> return total cost
        var totalCost = 0;
        for (var i in clientcart) {
            totalCost += clientcart[i].price * clientcart[i].count;
        }
        return totalCost.toFixed(2);
    };

    obj.listCart = function () { // -> array of Items
        var cartCopy = [];
        console.log("Listing cart");
        console.log(clientcart);
        for (var i in clientcart) {
            console.log(i);
            var item = clientcart[i];
            var itemCopy = {};
            for (var p in item) {
                itemCopy[p] = item[p];
            }
            itemCopy.total = (item.price * item.count).toFixed(2);
            cartCopy.push(itemCopy);
        }
        return cartCopy;
    };
    return obj;
})(); 