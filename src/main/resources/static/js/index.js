
function createShortUrl(){
        var inputUrl = document.getElementById("url").value;
        console.log("inputUrl:"+inputUrl);
        var errorElement = document.getElementById("error")


        if (validateInputUrl(inputUrl)){
                //
                getShortenUrl("createShortUrl", inputUrl)
                errorElement.setAttribute("style","display: none;color: red");

        }else {
                // Add Error validation Scenarios ...
                errorElement.setAttribute("style","display: block;color: red");

        }
}
function validateInputUrl(url) {

        //Add the URL Validation logic ...
        var validUrl = new RegExp(
            "^(http|https)\://[a-zA-Z0-9\-\.]+\.[a-zA-Z]{2,3}(:[a-zA-Z0-9]*)?/?([a-zA-Z0-9\-\._\?\,\'/\\\+&amp;%\$#\=~])*$");
        return validUrl.test(url);

}

function getShortenUrl(apiUrl, inputUrl) {
        console.log("Inside getShortenUrl:" + inputUrl)

        var xhr = new XMLHttpRequest();
        xhr.open("POST", apiUrl);

        xhr.setRequestHeader("Accept", "application/json");
        xhr.setRequestHeader("Content-Type", "application/json");

        xhr.onreadystatechange = function() {
                console.log("xhr object:"+xhr)
                if (xhr.readyState === 4) {
                        console.log(xhr.responseText);
                        var targetElement = document.getElementById("shortUrl");
                        targetElement.setAttribute("href","/"+xhr.responseText);
                        targetElement.removeAttribute("style");
                        targetElement.innerText= "http://localhost:8080/"+xhr.responseText;

                }
        };

        var data = {};
        data.inputURL = inputUrl;
        //var data = `{"inputURL": inputUrl
//}`;
        console.log(data);
        xhr.send(JSON.stringify(data));

}

