
function changeColor(select) {
  var option = select.options[select.selectedIndex];
  var td = option.parentNode;
  if (option.value == '++') {
    td.style.backgroundColor = '#56b048';
  } else if (option.value == '--') {
    td.style.backgroundColor = '#fa5b37';
  } else if (option.value == '- ') {
    td.style.backgroundColor = '#ffd3c9';
  } else if (option.value == '+ ') {
    td.style.backgroundColor = '#d1ffc9';
  } else if (option.value == '+-') {
    td.style.backgroundColor = '#dbdbdb';
  } else {
    td.style.backgroundColor = '';
  }
}