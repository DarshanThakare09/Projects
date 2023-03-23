const express = require('express');
const path = require('path');

app = express();
const port = 3000;

app.use('/static',express.static('static'));
app.use(express.urlencoded());

app.set('view engine','pug');
app.set('views',path.join(__dirname,'template'));

// *********************************************************************
const mongoose = require('mongoose');
main().catch(err=> console.log(err));
async function main(){
    mongoose.set('strictQuery', true);
    await mongoose.connect('mongodb://localhost:27017/Dnine');
}
const feedbacks = new mongoose.Schema({
    Name : String,
    Rating : String,
    Email : String,
    Feedback : String,
});
const feedback = mongoose.model('Feedbacks',feedbacks);



const askmes = new mongoose.Schema({
    Name : String,
    Email : String,
    Subject : String,
    Question : String,
});
const askme = mongoose.model('Askme',askmes);


const getintouchs = new mongoose.Schema({
    Name : String,
    Email : String,
    Subject : String,
    Message : String,
});
const getintouchwithme = mongoose.model('getintouch',getintouchs);



const contacts = new mongoose.Schema({
    Name : String,
    Email : String,
    Subject : String,
    meassage : String,
});
const contactme = mongoose.model('contact',contacts);
// *********************************************************************
app.get('/',(req,res)=>{
    res.status(200).render('home.pug');
})
app.get('/about',(req,res)=>{
    res.status(200).render('about.pug');
})
app.get('/projects',(req,res)=>{
    res.status(200).render('projects.pug');
})
app.get('/contact',(req,res)=>{
    res.status(200).render('contact.pug');
})
// *********************************************************************
app.post('/',(req,res)=>{
    var feed_back = new feedback(req.body);
    feed_back.save().then(()=>{
        res.status(200).render('home.pug');
    }).catch(()=>{
        res.status(404).render('error.pug');
    });
})
app.post('/ask-me',(req,res)=>{
    var ask_me = new askme(req.body);
    ask_me.save().then(()=>{
        res.status(200).render('contact.pug');
    }).catch(()=>{
        res.status(404).render('error.pug');
    });
})
app.post('/getintouchwith-me',(req,res)=>{
    var getintouchwith_me = new getintouchwithme(req.body);
    getintouchwith_me.save().then(()=>{
        res.status(200).render('contact.pug');
    }).catch(()=>{
        res.status(404).render('error.pug');
    });
})
app.post('/contact-me',(req,res)=>{
    var contact_me = new contactme(req.body);
    contact_me.save().then(()=>{
        res.status(200).render('contact.pug');
    }).catch(()=>{
        res.status(404).render('error.pug');
    });
})
// **********************************************************************
app.listen(port,()=>{
    console.log(`website is successfully running on http://127.0.0.1:${port}/`)
})