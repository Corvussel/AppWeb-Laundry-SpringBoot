CREATE DATABASE sistema_ventas;

USE sistema_ventas;

CREATE TABLE Clientes (
    ID_Cliente INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Correo_Electronico VARCHAR(100),
    Telefono VARCHAR(20),
    Direccion VARCHAR(255)
);

CREATE TABLE Empleados (
    ID_Empleado INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(50),
    Apellido VARCHAR(50),
    Cargo VARCHAR(50),
    Correo_Electronico VARCHAR(100),
    Telefono VARCHAR(20)
);

CREATE TABLE Ventas (
    ID_Venta INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATETIME,
    Total DECIMAL(10, 2),
    ID_Cliente INT,
    ID_Empleado INT,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente),
    FOREIGN KEY (ID_Empleado) REFERENCES Empleados(ID_Empleado)
);

CREATE TABLE Productos_Servicios (
    ID_Producto_Servicio INT AUTO_INCREMENT PRIMARY KEY,
    Nombre VARCHAR(100),
    Descripcion TEXT,
    Categoria VARCHAR(50),
    Precio DECIMAL(10, 2)
);

CREATE TABLE Detalles_Venta (
    ID_Detalle_Venta INT AUTO_INCREMENT PRIMARY KEY,
    ID_Venta INT,
    ID_Producto_Servicio INT,
    Cantidad INT,
    Precio DECIMAL(10, 2),
    FOREIGN KEY (ID_Venta) REFERENCES Ventas(ID_Venta),
    FOREIGN KEY (ID_Producto_Servicio) REFERENCES Productos_Servicios(ID_Producto_Servicio)
);
 

CREATE TABLE Gastos (
    ID_Gasto INT AUTO_INCREMENT PRIMARY KEY,
    Fecha DATETIME,
    Descripcion TEXT,
    Monto DECIMAL(10, 2)
);

CREATE TABLE Pedidos (
    ID_Pedido INT AUTO_INCREMENT PRIMARY KEY,
    ID_Cliente INT,
    Fecha_Pedido DATETIME,
    Estado VARCHAR(50),
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente)
);

CREATE TABLE WhatsApp_Comunicaciones (
    ID_Comunicacion INT AUTO_INCREMENT PRIMARY KEY,
    ID_Cliente INT,
    Fecha_Comunicacion DATETIME,
    Mensaje TEXT,
    FOREIGN KEY (ID_Cliente) REFERENCES Clientes(ID_Cliente)
);
