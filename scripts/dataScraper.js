import puppeteer from "puppeteer";
import fs from "fs";
//const readline = require('readline');
//var main = document.getElementById("main");
var PokemonList;

async function scrapeData(url, name){
    const browser = await puppeteer.launch();
    const page = await browser.newPage();
    await page.goto(url, {waitUntil: "domcontentloaded"});

    const getDexNum = await page.evaluate(() => {
        const dexNumElement = document.querySelector(".vitals-table strong");
        return "#0"+dexNumElement.innerHTML;
    });

    const getTypes = await page.evaluate(() => {
        try{
        const TypeElement = document.querySelectorAll(".type-icon");
        if (TypeElement.length == 1) return TypeElement.innerHTML + ":NA";
        else return TypeElement[0].innerHTML + ":" + TypeElement[1].innerHTML;
        }
        catch(e){
            console.log("Error");
        }
    });

    const getStats = await page.evaluate(() => {
        const HPElement = document.querySelectorAll(".cell-num");
        var val = "";
        for (let i = 0; i < 18; i+=3){
            val += HPElement[i].innerHTML
            console.log(i);
            if (i != 18) val += ":";
        }
        return val;
        
    });

    console.log(name+":"+getDexNum+":"+getTypes+":"+getStats);
    await browser.close();
    wait();
    /*
    function addColons(str){
        var arr = Array.from(str);
        var result = "";
        for (let i = 0; i < 12; i++){
            result += arr[i];
            if (i > 0 && i % 2 == 1 && i < 11) result += ":";
        }
        return result;
    }

    
    
    //console.log(getPrimaryType);

    /*
    //const[dexNum] = await page.$x('//*[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[1]/td/strong');
    const[primaryType] = await page.$x('//*[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[2]/td/a[1]');
    const[secondaryType] = await page.$x('//*[@id="tab-basic-1"]/div[1]/div[2]/table/tbody/tr[2]/td/a[2]');

    var numText = (await dexNum.getProperty('textContent')).jsonValue();
    var priText = (await primaryType.getProperty('textContent')).jsonValue();
    var secText = (await secondaryType.getProperty('textContent')).jsonValue();
    

    //main.innerHTML = numText + "\t" + priText + "\t" + secText;
    console.log("Pokedex#: "+numText + "\tPrimary: " + priText + "\tSecondary: " + secText);
    */
    
}

async function readData(){
    var path = "scripts/plist.txt";
    var array = fs.readFileSync(path).toString().split("\n");
    for (let i in array){
        wait();
        //console.log(array[i]);
        try{
        await scrapeData('https://pokemondb.net/pokedex/' + array[i], array[i]);
        }
        catch(e){
            console.log("Error (" + array[i] + "): " + e.toString());
        }
    }
    return;
}

function wait(){
    return new Promise((res) => {
        setTimeout(() => {
            res();}, 2000);});
}
/*
function getList(){
    fs.readFile("scripts/plist.txt", function (err, data) {
        if (err) {
        return console.error(err);
    }

        return data.toString().split('\n');    
    });
}

/*
PokemonList = getList();
console.log(PokemonList[0]);
*/
readData();
//scrapeData('https://pokemondb.net/pokedex/bulbasaur');
