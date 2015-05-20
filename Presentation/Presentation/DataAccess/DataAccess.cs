using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;

namespace DataAccess
{
    public class DataAccess
    {
        public IEnumerable<Stat> Test()
        {
            List<Stat> Dati = new List<Stat>();

            Stat d1 = new Stat()
            {
                Date = "20150501",
                Nation = "Italia",
                Language = "Java",
                Value = 10
            };
            Dati.Add(d1);
            Stat d2 = new Stat()
            {
                Date = "20150501",
                Nation = "Germania",
                Language = "Java",
                Value = 109
            };
            Dati.Add(d2);

            Stat d3 = new Stat()
            {
                Date = "20150501",
                Nation = "UK",
                Language = "Python",
                Value = 70
            };
            Dati.Add(d3);

            Stat d4 = new Stat()
            {
                Date = "20150501",
                Nation = "Belgio",
                Language = "C#",
                Value = 90
            };
            Dati.Add(d4);

            Stat d5 = new Stat()
            {
                Date = "20150501",
                Nation = "Francia",
                Language = "HTML",
                Value = 80
            };
            Dati.Add(d5);

            Stat d6 = new Stat()
            {
                Date = "20150501",
                Nation = "Francia",
                Language = "Python",
                Value = 80
            };
            Dati.Add(d6);

            Stat d7 = new Stat()
            {
                Date = "20150501",
                Nation = "UK",
                Language = "Java",
                Value = 80
            };
            Dati.Add(d7);

            Stat d8 = new Stat()
            {
                Date = "20150501",
                Nation = "Belgio",
                Language = "HTML",
                Value = 40
            };
            Dati.Add(d8);

            Stat d9 = new Stat()
            {
                Date = "20150501",
                Nation = "UK",
                Language = "Javascript",
                Value = 78
            };
            Dati.Add(d9);

            Stat d10 = new Stat()
            {
                Date = "20150501",
                Nation = "Italia",
                Language = "Javascript",
                Value = 88
            };
            Dati.Add(d10);

            Stat d11 = new Stat()
            {
                Date = "20150501",
                Nation = "Germania",
                Language = "C#",
                Value = 98
            };
            Dati.Add(d11);

            return Dati;
        }



    }
    }

