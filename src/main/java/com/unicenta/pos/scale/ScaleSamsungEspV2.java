//    uniCenta oPOS  - Touch Friendly Point Of Sale
//    Copyright (c) 2009-2023 uniCenta & previous Openbravo POS works
//    https://unicenta.com
//
//    This file is part of uniCenta oPOS
//
//    uniCenta oPOS is free software: you can redistribute it and/or modify
//    it under the terms of the GNU General Public License as published by
//    the Free Software Foundation, either version 3 of the License, or
//    (at your option) any later version.
//
//   uniCenta oPOS is distributed in the hope that it will be useful,
//    but WITHOUT ANY WARRANTY; without even the implied warranty of
//    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
//    GNU General Public License for more details.
//
//    You should have received a copy of the GNU General Public License
//    along with uniCenta oPOS.  If not, see <http://www.gnu.org/licenses/>.

package com.unicenta.pos.scale;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;

public class ScaleSamsungEspV2 implements Scale {

    private String result;
    private final String m_sPortScale;

    /** Creates a new instance of ScaleComm
     * @param sPortPrinter */
    public ScaleSamsungEspV2(String sPortPrinter) {
        m_sPortScale = sPortPrinter;
    }

    private final SerialPortDataListener dataListener = new SerialPortDataListener() {
        @Override
        public int getListeningEvents() {
            return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
        }

        @Override
        public void serialEvent(SerialPortEvent event) {
            if (event.getEventType() == SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                SerialPort serialPort = (SerialPort) event.getSerialPort();
                byte[] newData = new byte[serialPort.bytesAvailable()];
                serialPort.readBytes(newData, newData.length);
                String receivedData = new String(newData);
                result = result + receivedData;
            }
        }
    };

    /**
     *
     * @return
     */
    @Override
    public Double readWeight() {

        // Configura los parámetros del puerto serie
        SerialPort serialPort = SerialPort.getCommPort(m_sPortScale);
        serialPort.setBaudRate(4800);
        serialPort.setNumDataBits(8);
        serialPort.setNumStopBits(1);
        serialPort.setParity(1);

        // Abre el puerto serie
        if (serialPort.openPort()) {

            // Agrega el objeto de escucha al puerto serie
            serialPort.addDataListener(dataListener);

            // Escribe en el puerto serie
            result = "";
            byte[] data = new byte[] {0x24};
            serialPort.writeBytes(data, data.length);

            // Espera unos segundos para recibir la respuesta (puedes ajustar este tiempo)
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {}

            // Cierra el puerto serie
            serialPort.closePort();
        }

        try {
            return Double.valueOf(result);
        } catch (NumberFormatException e) {
            return 0.0;
        }
    }
}
