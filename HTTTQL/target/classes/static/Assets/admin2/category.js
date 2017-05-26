             jQuery.extend({
                            getValues: function (url) {
                                var result = null;
                                $.ajax({
                                    url: url,
                                    type: 'get',
                                    dataType: 'json',
                                    async: false,
                                    success: function (response) {
                                        result = response.data;
                                    }
                                });
                                return result;
                            }
                        });

                        loadItem();
                        var company = $.getValues("/ManagerCategory/GetCategory");

                        function removeName(name) {

                            for (var i = 0 ; i < company.length ; i++) {
                                if (company[i] == name) {
                                    company.slice(0, 1);
                                }
                            }
                        }

                        printSelect();

                        function printCompany(company) {

                            var html = '';
                            for (var i = company.length - 1 ; i >= 0  ; i--) {
                                html += '<div class="col-md-3" >'
                                               + '<button type="button" class="btn btn-default btn-block">'
                                               + '<span class="info-box-text">' + company[i] + '</span>'
                                             + '</button>'
                                              + '</div>';

                            }
                            $('.companygrib').html(html);
                        }
                        function printSelect() {
                            $("#congty").html("");
                            for (var i = company.length - 1 ; i >= 0  ; i--) {
                                $("#congty").append($('<option/>').html(company[i]));
                            }

                        }

                        $("#company").submit(function () {
                            $(".next-b2").removeClass("disabled");
                            var data = $(this).serializeArray();
                            //                       alert("Bạn đã thêm công ty  " + data[0].value);
                            company.push(data[0].value);
                            data_item.category = data[0].value + "";
                            saveItem();
                            $.ajax({
                                url: '/ManagerCategory/add',
                                type: 'POST',
                                data: { name: data[0].value.trim() },
                                success: function (response) {
                                    if (response.trim() == 'true') {
                                        swal(" OK !", " Thêm thành công !", "success")
                                        $(".sms").html("    <h4><i class='icon fa fa-check'></i> Bạn đã thêm công ty  :  " + data[0].value + "</h4>");

                                        printSelect();
                                        var arr = [];
                                        printCompany(arr);
                                        active(data[0].value);
                                    } else {
                                        sweetAlert(" Service ", response, "error");

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

                            $(this).find('input').val("");
                            return false;
                        });
                        $(".companygrib ").on("click", "button", function () {
                            $(".next-b3").removeClass("disabled");

                            $('.companygrib button .info-box-text').each(function (index) {
                                $(this).parent('button').removeClass('btn-success');
                            });
                            $(this).addClass('btn-success');
                            //   $(".sms").html("Bạn đã chọn  :  " + $(this).find(".info-box-text").html());
                            //                     data_item = $(this).find(".info-box-text").v + "";
                            swal(" Bạn chọn :  ", $(this).find(".info-box-text").html(), "success");


                            data_item.sub_category = $(this).find(".info-box-text").text();
                            saveItem();
                        });
                        $("select").change(function () {

                            $(".thangcho").show();
                            //           alert($(this).find(":selected").val());
                            data_item.category = $(this).find(":selected").val();
                            saveItem();
                            $(".sms").html("     <h4><i class='icon fa fa-check'></i> Bạn đã chọn  :  " + $(this).find(":selected").val() + "</h4>");

                            //////  in ra ds nhan phu 
                            var sendvalue = $(this).find(":selected").val();
                            $.ajax({
                                url: '/ManagerCategory/GetSub',
                                type: 'get',
                                dataType: 'json',
                                async: false,
                                data: { category: sendvalue },
                                success: function (response) {

                                    // printSelect();
                                    printCompany(response.data);
                                }
                            });


                            active($(this).find(":selected").val());





                            $(".next-b2").removeClass("disabled");

                        });
                        $("#search-company").keyup(function () {
                            SearchCompany($(this).val());
                        });
                        function active(name) {
                            $('.companygrib button .info-box-text').each(function (index) {
                                if (name.trim() === $(this).text().trim()) {
                                    $(this).parent('button').addClass('btn-success');
                                } else {
                                    $(this).parent('button').removeClass('btn-success');
                                }
                            });

                        }
                        $(".next-b2").click(function () {
                            if (data_item.category == null) {
                                sweetAlert("Error...", " Vui lòng chọn dnh mục !", "error");
                                return false;
                            } else {
                                $.ajax({
                                    url: '/ManagerCategory/block',
                                    type: 'POST',
                                    data: { name: data_item.category },
                                    success: function (response) {
                                        if ($.trim(response) == 'true') {

                                            swal("Good job!", " Khóa thành công", "success");
                                            window.location = "";
                                        } else {
                                            sweetAlert("Service..", " Không thể khóa !", "error");
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
                                $('.companygrib .col-md-3 .info-box-text').each(function (index) {
                                    if (data_item.category.trim() === $(this).text().trim()) {
                                        $(this).parents('.col-md-3').remove();
                                    }
                                });




                            }
                        });

                        $(".next-b3").click(function () {
                            if (data_item.sub_category == null) {
                                sweetAlert("Error..", " Vui lòng chọn danh mục phụ  !", "error");
                                return false;
                            } else {
                                $.ajax({
                                    url: '/ManagerCategory/blockSub',
                                    type: 'POST',
                                    data: { name: data_item.sub_category },
                                    success: function (response) {
                                        if ($.trim(response) == 'true') {

                                            swal("Good job!", " Khóa thành công", "success");
                                        } else {
                                            sweetAlert("Service..", " Không thể khóa !", "error");
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

                                $('.companygrib .col-md-3 .info-box-text').each(function (index) {
                                    if (data_item.sub_category.trim() === $(this).text().trim()) {
                                        $(this).parents('.col-md-3').remove();
                                    }
                                });

                                return false;

                            }
                        });
                        $(".next-add").click(function (event) {
                            /* Act on the event */
                            swal({
                                title: "Thêm danh mục phụ",
                                text: "Cho Danh mục " + data_item.category,
                                type: "input",
                                showCancelButton: true,
                                closeOnConfirm: false,
                                animation: "slide-from-top",
                                inputPlaceholder: "Write something"
                            },
                     function (inputValue) {
                         if (inputValue === false) return false;

                         if (inputValue === "") {
                             swal.showInputError(" Tên không được trông !");
                             return false
                         }
                         $.ajax({
                             url: '/ManagerCategory/addSub',
                             type: 'POST',
                             data: { name: data_item.category, subname: inputValue },
                             success: function (response) {
                                 if ($.trim(response.toLowerCase()) == 'true') {
                                     swal(" Ok !", " Thêm " + inputValue + " thành công ! ", "success");
                                     $.ajax({
                                         url: '/ManagerCategory/GetSub',
                                         type: 'get',
                                         dataType: 'json',
                                         async: false,
                                         data: { category: data_item.category },
                                         success: function (response) {

                                             // printSelect();
                                             printCompany(response.data);
                                         }
                                     });

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

                            return false;
                        });
                        function SearchCompany(name) {

                            $('.companygrib .col-md-3 .info-box-text').each(function (index) {
                                if (toAlias($(this).text().toLowerCase().trim()).indexOf(toAlias(name.trim().toLowerCase())) != -1) {
                                    $(this).parents('.col-md-3').show();
                                } else {
                                    $(this).parents('.col-md-3').hide();
                                }
                            });
                        }
                        function toAlias(str) {
                            str = str.toLowerCase();
                            str = str.replace(/à|á|ạ|ả|ã|â|ầ|ấ|ậ|ẩ|ẫ|ă|ằ|ắ|ặ|ẳ|ẵ/g, "a");
                            str = str.replace(/è|é|ẹ|ẻ|ẽ|ê|ề|ế|ệ|ể|ễ/g, "e");
                            str = str.replace(/ì|í|ị|ỉ|ĩ/g, "i");
                            str = str.replace(/ò|ó|ọ|ỏ|õ|ô|ồ|ố|ộ|ổ|ỗ|ơ|ờ|ớ|ợ|ở|ỡ/g, "o");
                            str = str.replace(/ù|ú|ụ|ủ|ũ|ư|ừ|ứ|ự|ử|ữ/g, "u");
                            str = str.replace(/ỳ|ý|ỵ|ỷ|ỹ/g, "y");
                            str = str.replace(/đ/g, "d");
                            str = str.replace(/!|\$|%|\^|\*|\(|\)|\+|\=|\<|\>|\?|\/|,|\.|\:|\'| |\"|\&|\#|\[|\]|~/g, "-");
                            str = str.replace(/-+-/g, "-"); //thay thế 2- thành 1-
                            str = str.replace(/^\-+|\-+$/g, "");
                            return str;
                        }
                        $(".overlay").slideUp(3000);