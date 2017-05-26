  var data_item = new Item("","","","",0,0,0,"","","");
  function Item( company, category ,sub_category, name , price ,old_price, qty, summanry , tags , img) {
        this.company = company ;
        this.category = category;
        this.sub_category = sub_category;
        this.name = name ;
        this.price = price ;
        this.old_price = old_price;
        this.qty = qty ;
        this.summanry = summanry ;
        this.tags  = tags  ;
        this.img = img ;
   }
  loadItem();
     function saveItem() {
        localStorage.setItem("addProduct-thanh",JSON.stringify(data_item));
    }
   function loadItem() {
        data_item = JSON.parse(localStorage.getItem("addProduct-thanh"));
        if (data_item === null) {
            data_item = new  Item(); 
        }
    }
     function  clearItem() {
        data_item = [];
        saveItem();
    }

     