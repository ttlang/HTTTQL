
jQuery.extend({
    getValuesPost: function (url) {
        var result = null;
        $.ajax({
            url: url,
            type: 'POST',
            dataType: 'json',
            async: false,
            success: function (response) {
                result = response;
            }
        });
        return result;
    },
    getValuesGet: function (url) {
        var result = null;
        $.ajax({
            url: url,
            type: 'GET',
            dataType: 'json',
            async: false,
            success: function (response) {
                result = response;
            }
        });
        return result;
    }
});







$(document).ready(function ($) {
    $('.crsl-item .add-to-cart').on('click', function () {
        // hieu ung mua hàng 
        var itemImg = $(this).parent().find('img').eq(0);
        flyToElement($(itemImg), $('.cart_anchor'));
        // end hieu ung
        // chuan bi them san pham 

        var root = $(this).parents(".crsl-item");
        var images = root.find(".thumbnail").find("img").attr("src");
        var price = root.find(".price").find(".new").text().replace(/,/g, "");
        //                          alert(name);
        var name = root.find(".postdate").text();
        var id = root.find(".postdate").attr("data-id");

        //                           shoppingCart.clearCart();
       
        $.ajax({
            url: '/Order/addItemToCart',
            type: 'POST',
            data: { product_id: parseInt(id), count: 1 },
            success: function (res) {

                if ($.trim(res) == 'true') {

                    displayCart();
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
    $("#clear-cart").click(function (event) {
        $.ajax({
            url: '/Order/clear',
            type: 'POST',
            success: function () {
                displayCart();
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
    $("#notificationsBody").on("click", ".delete-item", function (event) {
        var id = $(this).attr("data-id");
        $.ajax({
            url: '/Order/removeItemFromCart',
            type: 'POST',
            data: { product_id: id },
            success: function (res) {
                if ($.trim(res) == 'true') {
                    displayCart();
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

    $("#notificationsBody").on("click", ".subtract-item", function (event) {
        var id = $(this).attr("data-id");

        var num = parseInt($(this).parents(".clearfix").find('.item-count').val());
        num--;
        if (num <= 0) {
            alert("không thê giam số luong ");
        } else {
            $.ajax({
                url: '/Order/addItemToCart',
                type: 'POST',
                data: { product_id: id, count: num },
                success: function (res) {

                    if ($.trim(res) == 'true') {

                        displayCart();
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

    $("#notificationsBody").on("click", ".plus-item", function (event) {
        var name = $(this).attr("data-name");
        var id = $(this).attr("data-id");

        var num = parseInt($(this).parents(".clearfix").find('.item-count').val());
        num++;
        if (num >= 10) {
            alert("không thê tang số luong ");
        } else {

            $.ajax({
                url: '/Order/addItemToCart',
                type: 'POST',
                data: { product_id: id, count: num },
                success: function (res) {

                    if ($.trim(res) == 'true') {

                        displayCart();
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

    $("#notificationsBody").on("change", ".item-count", function (event) {
        var name = $(this).attr("data-name");
        var id = $(this).attr("data-id");
        var num = Number($(this).val());
        $.ajax({
            url: '/Order/addItemToCart',
            type: 'POST',
            data: { product_id: id, count: num },
            success: function (res) {

                if ($.trim(res) == 'true') {

                    displayCart();
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

});

// bac dau su  kien mua hàng



displayCart();
function displayCart() {

    var data = $.getValuesPost("/Order/orderCart");
    var cartArray = data.data;
    var countCart = $.getValuesGet("/Order/countCart");
    var totalCart = $.getValuesGet("/Order/totalCostInCart");

    console.log(data.data);

    if (cartArray.length !== 0) {
        $("#thanhtoan").show();
    } else {
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
        output += "<div class='clearfix'>"
         + "<img  src='/upload/resize?url=" + cartArray[i].img + "&w=150' class='productimg' width='50' height='50' ><h4>" + cartArray[i].name + "</h4>"
         + "<span class='item-price'>" + cartArray[i].price + "</span>"
         + "<button id='button' class='plus-item' data-id=" + cartArray[i].product_id + " data-name='"
            + cartArray[i].name + "'>+</button>"
            + " <button class='subtract-item' id='button' data-id=" + cartArray[i].product_id + "  data-name='"
            + cartArray[i].name + "'>-</button>"
            + " <button class='delete-item' id='button'  data-id=" + cartArray[i].product_id + "  data-name='"
            + cartArray[i].name + "'>X</button> <span class='quantity'> <input class='item-count' type='number'  data-id=" + cartArray[i].product_id + "  data-name='"
            + cartArray[i].name + "' value='" + cartArray[i].count + "'> </span> </div>  ";
    }
    $("#notificationsBody").html(output);
    $(".count-cart").html(countCart);
    $("#total-cart").html(totalCart);

}


// $(document).ready(function () {
// Danhmuc("dienthoai");
//    Danhmuc("laptop");
//    Danhmuc("linhkien");

//    function Danhmuc(danhmuc) {
//        var data = [];
//        data.push("Product");
//        data.push(danhmuc);
//        var x = Math.floor((Math.random() * 3) + 1);
//        var html = '<div class="col-l-7 col-12">'
//      + '<div  class="col-l-7 col-7 col-m-7 col-mb-7">'
//       + '  <div class="row">'
//        + ' <div class="prodouct-view-grid">'
//         + '<div class="' + danhmuc + '-left">'
//          + '</div>'
//       + '</div>'
//     + '</div>'
//     + '</div>'
//     + '<div style="background: pink ;  " class="col-l-5 col-5 col-m-5 col-mb-5">'
//      + '<div class="prodouct-view-grid">'
//      + '<div class="heading">Quảng cáo</div>'
//       + '<img style="width:100%;" src="images/qc/' + x + '.jpg">'
//       + '</div>'
//      + '</div>'
//      + '<div   style="background: pink ; " class="col-l-12 col-m-12">'
//       + '<span  class="tags">'
//           + '</span>  '
//     + '<div class="' + danhmuc + '-dot">'
//         + '</div>'
//      + '</div>'
// + '</div>'
// + '<div   class="col-l-5 col-12 col-m-12 col-mb-12">'
//  + '<div class="row  prodouct-view-grid  ' + danhmuc + '-right" >'
//   + '</div>'
// + '</div>';

//        var html2 = '<div style="max-width: 720px ; margin : 0 auto; overflow: hidden ; ">'
//         + '<img src="images/b6ef50ad5a18966891fabfdd10e2028c.jpg">'
//         + '</div>';


//        var dienthoai = $('.' + danhmuc);
//        dienthoai.append(displayCategory(data));
//        dienthoai.append($("<div/>").addClass('row').html(html));
//        dienthoai.append($("<div/>").addClass('row').html(html2));


//            $("." + danhmuc + "-right").html(displayGrib(dienthoai, 'col-l-6 col-3 col-m-3 col-mb-6', 12, 16));
//            $("." + danhmuc + "-dot").html(displayTags(dienthoai));
//            $("." + danhmuc + "-left").html(displayGrib(dienthoai, 'col-l-6 col-6 col-m-6 col-mb-6 tab', 0, 12));
//            boxSlides("." + danhmuc + "-left");
//    }


//    $.get("/UploadedFiles/data", function (data) {
//        var mydata = database;
//        var list = data.split("\n");
//        for (var i in list) {
//            var item = list[i].split("//");
//            // console.log(item);
//            mydata.addItemToDatabase(item[0] + "", item[1] + "", item[2] + "", item[3] + "", item[4] + "", item[5] + "");
//        }

//        $(".demotheo").html(displayGrib(mydata, 'col-l-3 col-4 col-m-6 col-mb-6 tab', 0, 12));


//        boxSlides(".demotheo");

//        // alert(count);


//        mydata.clear();
//    });
//    // v               // showSlides(".concat");
//    $.get("/UploadedFiles/data", function (data) {

//        var mydata = database;
//        var list = data.split("\n");
//        for (var i in list) {
//            var item = list[i].split("//");
//            // console.log(item);
//            mydata.addItemToDatabase(item[0] + "", item[1] + "", item[2] + "", item[3] + "", item[4] + "", item[5] + "");
//        }
//        var cartArray = mydata.listCart();
//        // console.log(cartArray);
//        // console.log(mydata.getCategory());

//        printBoxProduct(mydata, '#list-grib');
//        printBoxProduct(mydata, '#list-top-view');
//        function printBoxProduct(mydata, div) {

//            $(div).append(displayCategory(mydata.getCategory(true)));
//            $(div).append(displayGrib(mydata, 'col-l-3 col-4 col-m-6 col-mb-6 tab', 0, 12));
//            // console.log(mydata.listCart());
//            //   xoa du lien tai day                mydata.clear();
//            $(div + " .lables ul").on('click', 'li', function (event) {
//                event.preventDefault();
//                var category = $(this).attr("data-category");
//                if (category == 'all') {
//                    $(div + " .tab").each(function (index, el) {
//                        $(this).show();
//                    });
//                } else if (category == 'sale') {
//                    $(div + " .tab").each(function (i) {
//                        if ($(this).attr('sale') === 'yes') {
//                            $(this).show();
//                        } else {
//                            $(this).hide();
//                        }
//                    });
//                } else {
//                    $(div + " .tab").each(function (index, el) {
//                        if ($(this).attr("data-category") === category) {
//                            $(this).show();
//                        } else {
//                            $(this).hide();
//                        }
//                    });
//                }

//                $(div + ' .lables ul').each(function (i) {
//                    $(this).find('a').removeClass('active');
//                });
//                $(this).find('a').addClass('active');

//            });
//        }




//        // hieu ung show box view product
//        showboximages();

//        //end hieu ung show box view oriduct
//        // start hieu ung mua hang



//        // bac dau su  kien mua hàng
//        $('.crsl-item .add-to-cart').on('click', function () {
//            // hieu ung mua hàng 
//            var itemImg = $(this).parent().find('img').eq(0);
//            flyToElement($(itemImg), $('.cart_anchor'));
//            // end hieu ung
//            // chuan bi them san pham 
//            var root = $(this).parents(".crsl-item");
//            var images = root.find(".thumbnail").find("img").attr("src");
//            var price = root.find(".price").find(".new").text().replace(/,/g, "");
//            //                          alert(name);
//            var name = root.find(".postdate").text();
//            var id = root.find(".postdate").attr("data-id");

//            //                           shoppingCart.clearCart();
//            shoppingCart.addItemToCart(name, price, images, 1, id + "");

//            displayCart();
//        });
//        $("#clear-cart").click(function (event) {
//            shoppingCart.clearCart();
//            displayCart();
//        });
//        $("#notificationsBody").on("click", ".delete-item", function (event) {
//            var id = $(this).attr("data-id");
//            shoppingCart.removeItemFromCartAll(id + "");
//            displayCart();
//        });

//        $("#notificationsBody").on("click", ".subtract-item", function (event) {
//            var id = $(this).attr("data-id");
//            shoppingCart.removeItemFromCart(id + "");
//            displayCart();
//        });

//        $("#notificationsBody").on("click", ".plus-item", function (event) {
//            var name = $(this).attr("data-name");
//            var id = $(this).attr("data-id");
//            shoppingCart.addItemToCart(name, 0, "", 1, id + "");
//            displayCart();
//        });

//        $("#notificationsBody").on("change", ".item-count", function (event) {
//            var name = $(this).attr("data-name");
//            var id = $(this).attr("data-id");
//            var count = Number($(this).val());
//            shoppingCart.setCountForItem(name, count, id + "");
//            displayCart();
//        });


//        displayCart();

//        // hien thi gio hang
//        function displayCart() {
//            var cartArray = shoppingCart.listCart();
//            if (cartArray.length !== 0) {
//                $("#thanhtoan").show();
//            } else {
//                $("#thanhtoan").hide();
//            }
//            var output = '';
//            //                        <div class="clearfix"><img src="images/300x300.png" class="productimg">
//            //                                 <h4>Purple Jeggings </h4>
//            //                                <span class="item-price">10.910.000.000</span>
//            //                                <button id="button" class="plus-item" data-name="Purple Jeggings">+</button>
//            //                                <button class="subtract-item" id="button" data-name="Purple Jeggings">-</button> 
//            //                                   <button class="delete-item" id="button" data-name="Purple Jeggings">X</button> 
//            //                            <span class="quantity"> <input class="item-count" type="number" data-name="Purple Jeggings" value="7"> </span>
//            //                            </div> 
//            for (var i in cartArray) {
//                output += "<div class='clearfix'>"
//                 + "<img  src='" + cartArray[i].images + "' class='productimg' width='50' height='50' ><h4>" + cartArray[i].name + "</h4>"
//                 + "<span class='item-price'>" + cartArray[i].total + "</span>"
//                 + "<button id='button' class='plus-item' data-id=" + cartArray[i].id + " data-name='"
//                    + cartArray[i].name + "'>+</button>"
//                    + " <button class='subtract-item' id='button' data-id=" + cartArray[i].id + "  data-name='"
//                    + cartArray[i].name + "'>-</button>"
//                    + " <button class='delete-item' id='button'  data-id=" + cartArray[i].id + "  data-name='"
//                    + cartArray[i].name + "'>X</button> <span class='quantity'> <input class='item-count' type='number'  data-id=" + cartArray[i].id + "  data-name='"
//                    + cartArray[i].name + "' value='" + cartArray[i].count + "'> </span> </div>  ";
//            }
//            $("#notificationsBody").html(output);
//            $(".count-cart").html(shoppingCart.countCart());
//            $("#total-cart").html(shoppingCart.totalCart());
//            console.log(shoppingCart.listCart());
//        }
//        // ket thuc su kien mua hàng 


//    });

// });

// showslides(".lumia")
// showslides(".dienthoai");

// showSlides(".concat");



function boxSlides(nameClass) {
    var count = 0;
    var concat = $("<div/>").addClass("slideshow-container");
    var mydiv = $("<div/>").addClass("mySlides fade");
    $(nameClass + " .tab").each(function (index, el) {
        mydiv.append($(this));
        if ((index + 1) % 4 == 0) {
            concat.append(mydiv);
            mydiv = $("<div/>").addClass("mySlides fade");
            count++;
        }
    });

    var dot = slidesDot(count, $(nameClass).attr('class'));

    $(nameClass).html("");
    $(nameClass).append(concat);
    $(nameClass).append(dot);
    showslides(nameClass);


}


function slidesDot(count, nameClass) {
    var dot = $("<div/>").addClass("col-12 dot-center " + nameClass);
    var prev = $("<a/>").addClass("dot-prev").html("❮");
    dot.append(prev);
    var next = $("<a/>").addClass("dot-next").html("❯");
    for (var i = 1  ; i < count + 1 ; i++) {
        var span = $("<span/>");
        span.addClass("dot");
        span.attr("data-dot", i);
        dot.append(span);
    }
    return dot.append(next);
}


// h1   // Show silide chuyen dong
function showslides(name) {
    var slideIndex = 1;
    $(name + ' .dot').click(function (event) {
        var dot = $(this).attr("data-dot");
        currentSlide(dot);
    });
    $(name + " .dot-prev").click(function (event) {
        plusSlides(-1);
    });
    $(name + " .dot-next").click(function (event) {
        plusSlides(1);
    });

    showSlides(slideIndex);
    function plusSlides(n) {
        showSlides(slideIndex += n);
    }
    function auto() {
        plusSlides(slideIndex);
        setTimeout(auto, 5000);
    }
    auto();
    function currentSlide(n) {
        slideIndex = n;
        showSlides(slideIndex);
    }
    function showSlides(n) {
        var slides = $(name + " .mySlides");
        var dots = $(name + " .dot");
        if (n > slides.length) { slideIndex = 1 }
        if (n < 1) { slideIndex = slides.length }
        for (var i = 0; i < slides.length; i++) {
            slides[i].style.display = "none";
        }
        for (var i = 0; i < dots.length; i++) {
            dots[i].className = dots[i].className.replace(" active", "");
        }
        slides[slideIndex - 1].style.display = "block";
        dots[slideIndex - 1].className += " active";
    }
    return;
};
//  h1   // end show silide chuyen dong



function flyToElement(flyer, flyingTo) {
    var $func = $(this);
    var divider = 6;
    var flyerClone = $(flyer).clone();
    $(flyerClone).css({ position: 'absolute', top: $(flyer).offset().top + "px", left: $(flyer).offset().left + "px", opacity: 1, 'z-index': 1000 });
    $('body').append($(flyerClone));
    var gotoX = $(flyingTo).offset().left + ($(flyingTo).width() / 2) - ($(flyer).width() / divider) / 2;
    var gotoY = $(flyingTo).offset().top + ($(flyingTo).height() / 2) - ($(flyer).height() / divider) / 2;
    $(flyerClone).animate({
        opacity: 0.4,
        left: gotoX,
        top: gotoY,
        width: $(flyer).width() / divider,
        height: $(flyer).height() / divider
    }, 700,
function () {
    $(flyingTo).fadeOut('fast', function () {
        $(flyingTo).fadeIn('fast', function () {
            $(flyerClone).fadeOut('fast', function () {
                $(flyerClone).remove();
            });
        });
    });
});
}

showboximages();
function showboximages() {
    $('<div id="myModal" class="modal"><span class="close">×</span><img class="modal-content" id="img01"><div id="caption"> </div></div>').insertBefore('body');
    $(".thanh").click(function (event) {
        event.preventDefault();
        var modal = $("#myModal");
        var modalImg = $("#img01");
        var captionText = $("#caption");
        modal.css('display', 'block');
        modalImg.attr('src', $(this).attr("src") + "");
        modalImg.attr("alt", $(this).attr("alt") + "");
        captionText.html('<h2>' + $(this).attr("alt") + '<h2>');
    });
    $(".close").click(function (event) {
        $("#myModal").css("display", "none");
    });

    $(document).keyup(function (e) {
        if (e.keyCode == 27) { // escape key maps to keycode `27`
            // <DO YOUR WORK HERE>

            $("#myModal").css("display", "none");
        }
    });
    return;
};




//       <span  class="tags">
// <span class="price-tag"><a href="javascript:void()">Ao </a></span>   
//             </span>  
function displayTags(data) {
    var html = '';
    var category = data.getCategory(false);
    for (var i = 0 ; i < category.length ; i++) {
        html += '  <span  class="tags"><span class="price-tag"><a href="#">';
        html += category[i];
        html += '</a></span> </span>'
    }
    return html;
};

function displayCategory(data) {
    var html = "";
    html += '<div class="lables">'
                     + '<div id="crumbs">'
                       + '<ul>'
    for (var i in data) {
        html += '<li data-category="' + data[i] + '" ><a href="#">' + data[i] + '</a></li>';
    }
    html += '</ul>'
        + '</div>'
        + '</div>';
    return html;
}
//  data du lieu mang JSON
//layout reponsive
// indexFrom , hien thi bao nhieu san pham

function displayGrib(data, layout, index, indexFrom) {
    var tempHTML = '';
    // category , name , newprice ,oldprice , images , view                       
    var productlist = data;
    for (var i = index ; i < indexFrom ; i++) {
        var name = productlist[i].product_name;
        var price = productlist[i].price;
        var sale = productlist[i].sale;
        var newprice = price - (price * (sale / 100));
        var images = productlist[i].images;
        var id = productlist[i].product_id;
        var temp = 'sale="no"';
        if (sale != 0) {
            temp = 'sale="yes"';
        }
        tempHTML += '<div class="' + layout + '" ' + temp + ' data-category="" >'
                    + '<div class="crsl-item">'
                        + '<div class="thumbnail">'
                           + '<img class="thanh" id="myImg" src="/upload/resize?url=' + images + '&w=600" width="360" alt="danny antonucci">'
                           + '<span data-id="' + id + '" class="postdate"><a  style="color: white ;" href="/viewProduct?idp=' + id + '" >' + name + '</a> </span>'
                        + '</div>';
        if (sale != 0) {
            tempHTML += '<span class="sale">' + sale + '%</span>';
        }
        tempHTML += '<span class="price">'
             + '<div class="new">' + newprice.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</div>'
             + '<span class="old">' + price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",") + '</span>'
          + '</span>'
           + '<span class="add-prodouct add-to-cart" data-id="' + id + '"> </span>'
          + '</div>'
          + '</div>';

    }
    return tempHTML;
};


$('.crsl-item .add-to-cart').on('click', function () {
    //            // hieu ung mua hàng 
    var itemImg = $(this).parent().find('img').eq(0);
    flyToElement($(itemImg), $('.cart_anchor'));
});
///////// chuc nang gio hang



///////// end chuc nang gio hang











