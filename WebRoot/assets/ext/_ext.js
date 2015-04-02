String.prototype.replaceAll = function(s1, s2) {    
    return this.replace(new RegExp(s1, 'gm'), s2); //g全局   
}   
  
String.prototype.replaceAll2Excep = function(s1, s2) {      
    var temp = this;      
    while (temp.indexOf(s1) != -1) {      
        temp = temp.replace(s1, s2);      
    }      
    return temp;      
}    
//alert("abcedaaabs".replaceAll("a", "A"));   
//alert("abcedaaabs".replaceAll2Excep ("a", "A")); 