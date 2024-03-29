let URL='http://147.83.7.206:8080';//http://localhost:8080  http://147.83.7.206:8080
function signup() {

    var email = $('#email').val();
    var username = $('#username').val();
    var password = $('#password').val();
    var password2 = $('#password2').val();

    if (password == password2) {

        $.ajax({
            contentType: "application/json",
            type: 'POST',
            url: URL + '/dsaApp/user/signUp',
            data: JSON.stringify({"email": email, "username": username, "password": password}),
            dataType: 'json',
            success: function (result) {
                window.location.href = "login.html";
            },
            error: function (error) {
                console.log(error);
                if (email == null || username == null || password == null)
                    alert("You left something blank. Please try again!");
                else
                    alert("Username or email already in use. Please try again!");
            }
        });
    }
    else alert("Check the password.");
}
function login() {
    var username = $('#username').val();
    var password = $('#password').val();
    $.ajax({
        contentType: "application/json",
        type: 'POST',
        url: URL+'/dsaApp/user/logIn',
        data: JSON.stringify({ "username": username, "password": password }),
        dataType: 'json',
        success: function(result){
            localStorage.setItem("activeUser", username);
            localStorage.setItem("id", result.id);
            localStorage.setItem("coins", result.coins);
            localStorage.setItem("image",result.image);
            window.location.href = "Main.html";
        },
        error: function(error){
            if (username == "" || password == "")
                alert("You left something blank. Please try again!");
            else{
                alert("Wrong username or password. Please try again!");
            }
        }
    });
}
//if(localStorage.getItem("activeUser")!=null){
    function eradicate() {
        var id = localStorage.getItem("id");
        if (confirm("Are you sure? There is no way back!") == true) {
            $.ajax({
                contentType: "application/json",
                type: 'DELETE',
                url: URL + '/dsaApp/user/delete/' + id,
                dataType: 'json',
                success: function (result) {
                    localStorage.setItem("id", null);
                    window.location.href = "index.html";
                }
            });
        }
    }

    function logout() {
        localStorage.clear();
        window.location.href = "login.html";
    }

    function updateUsername() {
        var oldUsername = $('#oldUsername').val();
        var newUsername = $('#newUsername').val();
        $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: URL + '/dsaApp/user/updateUser/' + oldUsername + '/' + newUsername,
            data: JSON.stringify({"oldUsername": oldUsername, "newUsername": newUsername}),
            dataType: 'json',
            success: function (result) {
                localStorage.setItem("activeUser", newUsername);
                window.location.href = "Main.html";
            },
            error: function (error) {
                if (oldUsername == "" || newUsername == "")
                    alert("You left something blank. Please try again!");
                else {
                    alert("Username already in use. Please try again!");
                }
            }
        });
    }

    function updatePassword() {
        var oldPassword = $('#oldPassword').val();
        var newPassword = $('#newPassword').val();
        var id = localStorage.getItem("id");
        $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: URL + '/dsaApp/user/updatePassword',
            data: JSON.stringify({"id": id, "oldPassword": oldPassword, "newPassword": newPassword}),
            dataType: 'json',
            success: function (result) {
                window.location.href = "Main.html";
            },
            error: function (error) {
                if (oldPassword == "" || newPassword == "")
                    alert("You left something blank. Please try again!");
                else {
                    alert("Wrong password");
                }
            }
        });
    }

    function image1(){
        var id = localStorage.getItem("id");
        var newImage = 1;
        $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: URL + '/dsaApp/user/updateImage/' + id + '/' + newImage,
            data: JSON.stringify({"id": id, "newImage": newImage}),
            dataType: 'json',
            success: function (result) {
                localStorage.setItem("image", newImage);
                window.location.href = "Main.html";
            },
            error: function (error) {
                alert("Something went wrong. Please try again!");
            }
        });
    }

    function image2() {
        var id = localStorage.getItem("id");
        var newImage = 2;
        $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: URL + '/dsaApp/user/updateImage/' + id + '/' + newImage,
            data: JSON.stringify({"id": id, "newImage": newImage}),
            dataType: 'json',
            success: function (result) {
                localStorage.setItem("image", newImage);
                window.location.href = "Main.html";
            },
            error: function (error) {
                alert("Something went wrong. Please try again!");
            }
        });
    }

    function image3(){
        var id = localStorage.getItem("id");
        var newImage = 3;
        $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: URL + '/dsaApp/user/updateImage/' + id + '/' + newImage,
            data: JSON.stringify({"id": id, "newImage": newImage}),
            dataType: 'json',
            success: function (result) {
                localStorage.setItem("image", newImage);
                window.location.href = "Main.html";
            },
            error: function (error) {
                alert("Something went wrong. Please try again!");
            }
        });
    }

    function image4(){
        var id = localStorage.getItem("id");
        var newImage = 4;
        $.ajax({
            contentType: "application/json",
            type: 'PUT',
            url: URL + '/dsaApp/user/updateImage/' + id + '/' + newImage,
            data: JSON.stringify({"id": id, "newImage": newImage}),
            dataType: 'json',
            success: function (result) {
                localStorage.setItem("image", newImage);
                window.location.href = "Main.html";
            },
            error: function (error) {
                alert("Something went wrong. Please try again!");
            }
        });
    }

    function getItemsList(){
        $.ajax({
            type: 'GET',
            url: "/dsaApp/item/itemsList",
            dataType: 'json',
            success:function (result) {
                for (let i = 0; i < result.length; i++) {
                    console.log("i: "+i, result[i]);
                    $("#itemsTable").append(
                        "<tr> <td>" + result[i].name +
                        "</td> <td>" + result[i].description +
                        "</td> <td>" + result[i].price +
                        "</td><td>" +  '<img src ="images/' + result[i].image + '.png" width = "100" height ="100">' + "</td></tr>" +
                        '</td><td>' + '<button type = "button" class = "button" id="' + result[i].id +
                        '" onclick="PurchaseItem(this.id)"  >Purchase</button>' + '</td> </tr>');

                }},

            error: function (error) {
                alert("Unable to get Shop data.");
                console.log(error);
                window.location.href = "Main.html";
            }
        })
    }

    function getRanking() {
        $.ajax({
            type: 'GET',
            url: "/dsaApp/match/ranking",
            dataType: 'json',
            success: function (result) {
                for (let i = 0; i < result.length; i++) {
                    $("#rankingTable").append(
                        "<tr> <td>" + result[i].username +
                        "</td> <td>" + result[i].maxPoints + "</td> </tr>");
                }
            },

            error: function (error) {
                alert("Unable to get Leaderboard data.");
                console.log(error);

            }
        })
    }
    function getInventoryList(){
        var userid = localStorage.getItem("id");
        $.ajax({
            type: 'GET',
            url: '/dsaApp/item/inventory/' + userid,
            dataType: 'json',
            success: function (result) {
                for (let i = 0; i < result.length; i++) {
                    $("#inventoryTable").append(
                        "<tr> <td>" + result[i].name +
                        "</td> <td>" + result[i].description +
                        "</td><td>" +  '<img src ="images/' + result[i].image + '.png" width = "100" height ="100">' + "</td></tr>");
                }
            },

            error: function (error) {
                alert("Unable to get Inventory data.");
                console.log(error);
            }
        })

    }

    function PurchaseItem(item) {
        var userName = localStorage.getItem("activeUser");
        var idItem = item;
        $.ajax({
            type: 'PUT',
            url: "dsaApp/item/PurchaseItem/" + idItem + "/" + userName,
            dataType: 'json',
            success: function (result) {
                localStorage.setItem("coins", result.coins);
                window.location.href = "store.html";
                alert('Bought succesfully');
            },
            error: function (error) {
                alert('Purchase failed');
            },
        })
    }
/*}
else{
    window.location.href = "login.html";
}*/





