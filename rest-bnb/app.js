const express = require('express');
const app = express();
app.use(express.json());

let cuentas = [{ cuenta: "BNB-123", ci: "111", nombres: "Juan", apellidos: "Perez", saldo: 500.50 }];

// Recurso principal: /cuenta [cite: 47, 48, 49]
app.get('/cuenta/:id', (req, res) => {
    let cuenta = cuentas.find(c => c.cuenta === req.params.id);
    res.json(cuenta || { error: "No encontrada" });
});

app.put('/cuenta/:id', (req, res) => {
    let cuenta = cuentas.find(c => c.cuenta === req.params.id);
    if(cuenta) {
        cuenta.saldo += req.body.monto;
        res.json({ mensaje: "Saldo actualizado", cuenta });
    } else {
        res.status(404).json({ error: "No encontrada" });
    }
});

app.listen(4000, () => console.log('REST BNB en puerto 4000'));