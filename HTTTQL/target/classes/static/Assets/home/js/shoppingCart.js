  function Ajaxupdata(){
        $.get("data", function( data ) {
            alert("dsdsd");
            var list = data.split("\n");
               for( var i in list ){
                  var item = list[i].split("//");
                  // console.log(item);
                     var item = new Item(item[0]+"", item[1]+"", item[2]+"",item[3]+"",item[4]+"",item[5]+"") ;
                     cart.push(item);
           }
 
      });
  } ;
 var database = (function () {
 	// body...
 	 var cart = [] ;
 	 function Item( category , name , newprice ,oldprice , images , view  ){
               this.category = category ;
               this.name = name ;
               this.newprice = newprice ;
               this.images = images ;
               this.view = view ;
 	 }
 	 var obj = {} ;
  // public method and properties 
  obj.addItemToDatabase = function(category , name , newprice ,oldprice , images , view ) {
          var item = new Item(category , name , newprice ,oldprice , images , view );
          cart.push(item);
        return ;
  };
   obj.listCart = function(){
   	var cartCopy = [];
   	   for( var i in cart){
   	   	 var item = cart[i] ;
   	   	     var itemCoppy = {} ;
   	   	    for(var p in item ){
                 itemCoppy[p] = item[p] ;
   	   	    }
   	   	    cartCopy.push(itemCoppy);
   	   }
   	return cartCopy ;
   }
   return obj ;
    
 })();
 