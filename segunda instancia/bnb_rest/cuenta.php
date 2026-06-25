<?php
// Configuración de cabeceras para permitir CORS y definir que responderemos en JSON
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: GET, POST, PATCH, DELETE");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");

$host = "localhost";
$db_name = "db_bnb";
$username = "root";
$password = "";

try {
    $conn = new PDO("mysql:host=$host;dbname=$db_name", $username, $password);
    $conn->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
} catch(PDOException $exception) {
    echo json_encode(["error" => "Error de conexión: " . $exception->getMessage()]);
    exit();
}

// Obtener el método HTTP (GET, POST, PATCH, DELETE)
$metodo = $_SERVER['REQUEST_METHOD'];

// Obtener los datos del cuerpo de la petición (para POST y PATCH)
$input = json_decode(file_get_contents('php://input'), true);

switch ($metodo) {
    case 'GET':
        // EJEMPLO: http://localhost/bnb_rest/cuenta.php?nro_cuenta=12345
        if (isset($_GET['nro_cuenta'])) {
            $nro_cuenta = $_GET['nro_cuenta'];
            $query = "SELECT * FROM cuentas WHERE nro_cuenta = :nro_cuenta";
            $stmt = $conn->prepare($query);
            $stmt->bindParam(":nro_cuenta", $nro_cuenta);
            $stmt->execute();
            
            if ($stmt->rowCount() > 0) {
                $row = $stmt->fetch(PDO::FETCH_ASSOC);
                echo json_encode(["exito" => true, "datos" => $row]);
            } else {
                http_response_code(404);
                echo json_encode(["exito" => false, "mensaje" => "Cuenta no encontrada"]);
            }
        } else {
            // Si no se pasa cuenta, devuelve todas
            $query = "SELECT * FROM cuentas";
            $stmt = $conn->prepare($query);
            $stmt->execute();
            $cuentas = $stmt->fetchAll(PDO::FETCH_ASSOC);
            echo json_encode(["exito" => true, "datos" => $cuentas]);
        }
        break;

    case 'POST':
        // EJEMPLO { "nro_cuenta": "99999", "moneda": "Bs", "saldo": 1000.50 }
        if (isset($input['nro_cuenta']) && isset($input['moneda']) && isset($input['saldo'])) {
            try {
                $query = "INSERT INTO cuentas (nro_cuenta, moneda, saldo) VALUES (:nro_cuenta, :moneda, :saldo)";
                $stmt = $conn->prepare($query);
                $stmt->bindParam(":nro_cuenta", $input['nro_cuenta']);
                $stmt->bindParam(":moneda", $input['moneda']);
                $stmt->bindParam(":saldo", $input['saldo']);
                
                if ($stmt->execute()) {
                    http_response_code(201);
                    echo json_encode(["exito" => true, "mensaje" => "Cuenta creada exitosamente"]);
                }
            } catch (PDOException $e) {
                http_response_code(400);
                echo json_encode(["exito" => false, "mensaje" => "Error al crear cuenta: " . $e->getMessage()]);
            }
        } else {
            http_response_code(400);
            echo json_encode(["exito" => false, "mensaje" => "Faltan datos (nro_cuenta, moneda, saldo)"]);
        }
        break;

    case 'PATCH':
        // TRANFERENCIA: { "nro_cuenta": "12345", "operacion": "sumar", "monto": 50 }
        if (isset($input['nro_cuenta']) && isset($input['operacion']) && isset($input['monto'])) {
            $nro_cuenta = $input['nro_cuenta'];
            $monto = floatval($input['monto']);
            $operacion = $input['operacion']; 

            $query_check = "SELECT saldo FROM cuentas WHERE nro_cuenta = :nro_cuenta";
            $stmt_check = $conn->prepare($query_check);
            $stmt_check->bindParam(":nro_cuenta", $nro_cuenta);
            $stmt_check->execute();

            if ($stmt_check->rowCount() > 0) {
                $row = $stmt_check->fetch(PDO::FETCH_ASSOC);
                $saldo_actual = floatval($row['saldo']);
                $nuevo_saldo = $saldo_actual;

                if ($operacion === 'sumar') {
                    $nuevo_saldo = $saldo_actual + $monto;
                } elseif ($operacion === 'restar') {
                    if ($saldo_actual >= $monto) {
                        $nuevo_saldo = $saldo_actual - $monto;
                    } else {
                        http_response_code(400);
                        echo json_encode(["exito" => false, "mensaje" => "Fondos insuficientes"]);
                        exit();
                    }
                }

                $query_update = "UPDATE cuentas SET saldo = :nuevo_saldo WHERE nro_cuenta = :nro_cuenta";
                $stmt_update = $conn->prepare($query_update);
                $stmt_update->bindParam(":nuevo_saldo", $nuevo_saldo);
                $stmt_update->bindParam(":nro_cuenta", $nro_cuenta);

                if ($stmt_update->execute()) {
                    echo json_encode(["exito" => true, "mensaje" => "Saldo actualizado", "nuevo_saldo" => $nuevo_saldo]);
                }
            } else {
                http_response_code(404);
                echo json_encode(["exito" => false, "mensaje" => "Cuenta no encontrada"]);
            }
        } else {
            http_response_code(400);
            echo json_encode(["exito" => false, "mensaje" => "Faltan datos (nro_cuenta, operacion, monto)"]);
        }
        break;

    case 'DELETE':
        //http://localhost/bnb_rest/cuenta.php?nro_cuenta=12345
        if (isset($_GET['nro_cuenta'])) {
            $nro_cuenta = $_GET['nro_cuenta'];
            $query = "DELETE FROM cuentas WHERE nro_cuenta = :nro_cuenta";
            $stmt = $conn->prepare($query);
            $stmt->bindParam(":nro_cuenta", $nro_cuenta);
            $stmt->execute();
            
            if ($stmt->rowCount() > 0) {
                echo json_encode(["exito" => true, "mensaje" => "Cuenta eliminada"]);
            } else {
                http_response_code(404);
                echo json_encode(["exito" => false, "mensaje" => "Cuenta no encontrada para eliminar"]);
            }
        } else {
            http_response_code(400);
            echo json_encode(["exito" => false, "mensaje" => "Debe proporcionar nro_cuenta para eliminar"]);
        }
        break;

    default:
        http_response_code(405);
        echo json_encode(["exito" => false, "mensaje" => "Método no permitido"]);
        break;
}
?>

