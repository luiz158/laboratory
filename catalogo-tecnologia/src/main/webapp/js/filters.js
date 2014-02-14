

var filters = angular.module('catalogo.filters',[]);

filters.filter('tamanho', function() {
    return function humanFileSize(bytes) {
        var thresh = 1024;
        if(bytes < thresh) return bytes + ' B';
        var units = ['kB','MB','GB','TB','PB','EB','ZB','YB'];
        var u = -1;
        do {
            bytes /= thresh;
            ++u;
        } while(bytes >= thresh);
        return bytes.toFixed(1)+' '+units[u];
    }
  });
