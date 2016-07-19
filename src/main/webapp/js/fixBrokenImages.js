/* 
 * FixBrokenImages
 * based on Mootools
 * author: Emiliano Armellin
 * vers: 1.0
 * date: 16/10/2009
 *
 * substitute broken URL with 'nd.jpg' on the same path
 */
window.addEvent('domready', function(){
    $$('img').each(function(element){
                element.addEvent('error', function(){
                    var src = this.get('src');
                    if(src.contains('nd.jpg')){
                        this.removeEvents('error');
                    }
                    var path = src.substring(0, src.lastIndexOf('/')+1);
                    this.set('src', path+'nd.jpg');
                });
                element.src = element.src;
    });
})


