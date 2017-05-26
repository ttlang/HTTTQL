                function boxSlides( nameClass ){
                                var count =  0 ; 
                             var concat = $("<div/>").addClass("slideshow-container");
                              var mydiv = $("<div/>").addClass("mySlides fade");
                       $(nameClass+" .tab").each(function(index, el) {
                                mydiv.append($(this));
                                if((index + 1 )%4==0){
                                 concat.append(mydiv);
                                  mydiv = $("<div/>").addClass("mySlides fade");
                                    count++;            
                                 }
                            });              
                          var dot = slidesDot( count , $(nameClass).attr('class') );
                                   
                          $(nameClass).html("");
                           $(nameClass).append(concat);
                             $(nameClass).append(dot) ;
                         showslides(nameClass);
                       
                          }
                                    
                         function slidesDot( count , nameClass ){
                                 var dot = $("<div/>").addClass("col-12 dot-center "+nameClass);
                          var prev = $("<a/>").addClass("dot-prev").html("❮");
                              dot.append(prev) ;
                            var next = $("<a/>").addClass("dot-next").html("❯");
                            for(var i = 1  ; i< count+1 ; i++){
                                  var span  = $("<span/>") ;
                                     span.addClass("dot");
                                     span.attr("data-dot" , i ) ;
                                     dot.append(span);
                            }
                            return dot.append(next) ;
                         }


         // h1   // Show silide chuyen dong
                         function showslides( name ){
                            var slideIndex = 1;
                     $(name+' .dot').click(function(event) {
                         var  dot  = $(this).attr("data-dot");
                         currentSlide(dot);
                        }); 
                   $(name+" .dot-prev").click(function(event) {
                       plusSlides(-1);
                         });
                  $(name+" .dot-next").click(function(event) {
                       plusSlides(1);
                       }) ;  
               
                    showSlides(slideIndex);
                 function plusSlides(n) {
                      showSlides(slideIndex += n);
                      }
             function auto(){
              plusSlides(slideIndex);
              setTimeout(auto , 5000);
                }
                   auto();
                function currentSlide(n) {
                   slideIndex = n ;
              showSlides(slideIndex);
                   }
                function showSlides(n) {
                   var slides  = $(name+" .mySlides");
                 var dots = $(name+" .dot");
               if (n > slides.length) {slideIndex = 1}
              if (n < 1) {slideIndex = slides.length}
                 for (var i = 0; i < slides.length; i++) {
                   slides[i].style.display = "none";
                       }
                for (var i = 0; i < dots.length; i++) {
               dots[i].className = dots[i].className.replace(" active", "");
                   }
                   slides[slideIndex-1].style.display = "block";
                   dots[slideIndex-1].className += " active";
                  }
                  return ;
              };