window.onload = function() {
  update();
};

function saveData(textarea){
    console.log(textarea.value);
    content = {
        text : textarea.value
    }
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "save-data",
        data : JSON.stringify(content),
        success : function() {
            console.log("SUCCESS");
        },
        error : function(e) {
            console.log("ERROR: ", e);
        }
    });
}

function update(){
    var textarea = document.getElementById('text');
    content = {
        text : textarea.value
    }
    $.ajax({
        type : "POST",
        contentType : "application/json",
        url : "update",
        data : JSON.stringify(content),
        success : function(data) {
            console.log("SUCCESS: ", data);
            textarea.value = data.text;
        },
        error : function(e) {
            console.log("ERROR: ", e);
        },
        complete: function () {
            update();
        }
    });
}