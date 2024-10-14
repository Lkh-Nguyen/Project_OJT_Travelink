    
const $ = document.querySelector.bind(document);
const $$ = document.querySelectorAll.bind(document);

const aboutLink = $('.nav__links .link:nth-child(2) a');
const booking__nav = $$('.booking__nav span');

const numberLabel = $('.form__group:nth-child(2) .input__content');
const travellersDropdown = document.getElementById('travellersDropdown');
const adultsInput = document.getElementById('adults');
const childrenInput = document.getElementById('children');
const numberInput = document.getElementById('numberInput');
//aboutLink.addEventListener('click', (e) =>{
//    e.preventDefault();
//    const aboutSection = $('#about');
//    aboutSection.scrollIntoView({
//        behavior:'smooth'
//    });
//}
//);
booking__nav.forEach(e =>{
    e.addEventListener('click', function () {
        booking__nav.forEach(item => item.classList.remove('active'));
        this.classList.add('active');
    });
});

console.log(numberLabel);
numberLabel.addEventListener('click', () =>{
    travellersDropdown.style.display = 'block';
});

document.addEventListener('click',(e)=>{
    if (!numberLabel.contains(e.target)
        &&!travellersDropdown.contains(e.target)){
        travellersDropdown.style.display = 'none';
    }
});

applyTravellers.addEventListener('click', function (e) {
    e.preventDefault();
    const adults = adultsInput.value;
    const children = childrenInput.value;
    document.getElementById('number_label').innerHTML = `${adults} Adults, ${children} Children`;
    travellersDropdown.style.display = 'none';
});