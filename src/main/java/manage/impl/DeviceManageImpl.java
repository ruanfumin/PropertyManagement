package manage.impl;

import dao.DeviceDao;
import dao.impl.DeviceDaoImpl;
import entity.Device;
import exception.DeviceException;
import manage.DeviceManage;
import java.sql.SQLException;
import java.util.List;

/**
 * @author: 我的袜子都是洞
 * @description:
 * @path: PropertyManagement-manage.impl-DeviceManageImpl
 * @date: 2019-04-30 01:18
 */
public class DeviceManageImpl implements DeviceManage {

    private DeviceDao deviceDao = new DeviceDaoImpl();

    /**
     * 根据did获取某设备信息
     *
     * @param did
     * @return
     * @throws DeviceException
     */
    @Override
    public Device getDevice(int did) throws DeviceException {
        if (did < 1) {
            return null;
        }
        try {
            Device device = deviceDao.getDevice(did);
            return device;
        } catch (SQLException e) {
            throw new DeviceException(e.getMessage());
        }
    }

    /**
     * 添加设备信息
     *
     * @param device
     * @return
     * @throws DeviceException
     */
    @Override
    public boolean saveDevice(Device device) throws DeviceException {
        String device_name = device.getDevice_name();
        String device_type = device.getDevice_type();
        String processing_opinion = device.getProcessing_opinion();
        String user = device.getUser();
        if ("".equals(user) ||
                "".equals(device_name) ||
                "".equals(device_type) ||
                "".equals(processing_opinion)
        ) {
            return false;
        }
        try {
            boolean flag = deviceDao.saveDevice(device);
            if (flag) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DeviceException(e.getMessage());
        }
    }

    /**
     * 删除某设备信息
     *
     * @param did
     * @return
     * @throws DeviceException
     */
    @Override
    public boolean deleteDevice(int did) throws DeviceException {
        if (did < 1) {
            return false;
        }
        try {
            boolean flag = deviceDao.deleteDevice(did);
            if (flag) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DeviceException(e.getMessage());
        }
    }

    /**
     * 修改某设备信息
     *
     * @param device
     * @return
     * @throws DeviceException
     */
    @Override
    public boolean updateDevice(Device device) throws DeviceException {
        String device_name = device.getDevice_name();
        String device_type = device.getDevice_type();
        String processing_opinion = device.getProcessing_opinion();
        if (
                "".equals(device_name) ||
                "".equals(device_type) ||
                "".equals(processing_opinion)
        ) {
            return false;
        }
        try {
            boolean flag = deviceDao.updateDevice(device);
            if (flag) {
                return true;
            } else {
                return false;
            }
        } catch (SQLException e) {
            throw new DeviceException(e.getMessage());
        }
    }

    /**
     * 列出设备信息
     *
     * @return
     * @throws DeviceException
     */
    @Override
    public List<Device> listDevice() throws DeviceException {
        try{
            List<Device> list = deviceDao.listDevice();
            return list;
        } catch (SQLException e) {
            throw new DeviceException(e.getMessage());
        }
    }
}