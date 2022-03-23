function register() {
    let uname = document.getElementById("tb_1").value
    let pw = document.getElementById("tb_2").value
    let url = "http://185.194.217.213:8080/api/user/newUser?key=dubsky120958120958120985&username="+uname+"&password="+pw;

    let xhr = new XMLHttpRequest();
    xhr.open("POST", url);

    xhr.setRequestHeader("Accept", "application/json");
    xhr.setRequestHeader("Content-Type", "application/json");
    
    xhr.onreadystatechange = function () {
   if (xhr.readyState === 4) {
      console.log(xhr.responseText);
   }};
    
    xhr.send();
}