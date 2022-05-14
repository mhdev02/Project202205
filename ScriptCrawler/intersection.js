exports.intersection = function(...args) {
    let result = [];
    let containers = [];    

    for(let i = 0; i < args.length; i++) {
      let container = {};
      for(let j = 0; j < args[i].length; j++) {
        if(!container[args[i][j]]) container[args[i][j]] = 1;
        else container[args[i][j]]++;
      }
      containers.push(container);
    }   

    let keys = Object.keys(containers[0]);    

    for(let i = 0; i <= keys.length; i++) {
      let flag = false;
      let value = [];
      for(let j = 0; j < containers.length; j++) {
        if(!containers[j][keys[i]]) {
          flag = true;  
        } else {
          value.push(containers[j][keys[i]]);
        }
      }
      if(!flag) {
        for(let k = 0; k < Math.min.apply(null, value); k++) {
          result.push([keys[i]]);
        }
      } 
    }
    return result.flat();
};

