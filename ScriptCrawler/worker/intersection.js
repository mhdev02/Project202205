exports.intersection = function (...args) {

  let result = [];
  let containers = [];
  let returnArr = [];

  for (let i = 0; i < args.length; i++) {
    let container = {};
    for (let j = 0; j < args[i].length; j++) {
      if (!container[args[i][j]]) container[args[i][j]] = 1;
      else container[args[i][j]]++;
    }
    containers.push(container);
  }

  let keys = Object.keys(containers[0]);

  for (let i = 0; i <= keys.length; i++) {
    let flag = false;
    let value = [];
    for (let j = 0; j < containers.length; j++) {
      if (!containers[j][keys[i]]) {
        flag = true;
      } else {
        value.push(containers[j][keys[i]]);
      }
    }
    if (!flag && (keys[i].length !== 0)) {
      result.push([keys[i], Math.min.apply(null, value)]);
    }
  }
  result.sort((a, b) => b[1] - a[1]);
  
  for (let i = 0; i < result.length; i++) {
    returnArr.push(`${result[i][0]}: ${result[i][1]}`);
  }
  return returnArr;
};