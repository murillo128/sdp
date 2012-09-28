/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.murillo.sdp.impl;


import org.murillo.abnf.Rule$bandwidth_field;
import org.murillo.abnf.Rule$connection_field;
import org.murillo.abnf.Rule$information_field;
import org.murillo.abnf.Rule$key_field;
import org.murillo.abnf.Rule$media_description;
import org.murillo.abnf.Rule$attribute_field;
import org.murillo.sdp.Attribute;
import org.murillo.sdp.Bandwitdh;
import org.murillo.sdp.Connection;
import org.murillo.sdp.Information;
import org.murillo.sdp.Key;
import org.murillo.sdp.MediaDescription;
/**
 *
 * @author Sergio
 */
class MediaDescriptionBuilder extends  Builder {

    public MediaDescription media;

    @Override
    public Object visit(Rule$media_description rule) {
        //New object
        media = new MediaDescription();
        //Parse it
        super.visit(rule);
        //return media
        return media;
    }

    @Override
    public Object visit(Rule$key_field rule) {
        //Create builder
        KeyBuilder builder = new KeyBuilder();
        //Generate it
        Key key = (Key)builder.visit(rule);
        //Add it
        media.setKey(key);
        //Return it
        return key;
    }

    @Override
    public Object visit(Rule$connection_field rule) {
        //Create builder
        ConnectionBuilder builder = new ConnectionBuilder();
        //Parse it
        Connection connection = (Connection)builder.visit(rule);
        //Set it
        media.addConnection(connection);
        //Return connection
        return connection;
    }

    @Override
    public Object visit(Rule$bandwidth_field rule) {
        //Create builder
        BandwitdhBuilder builder = new BandwitdhBuilder();
        //Generate it
        Bandwitdh bandwidth = (Bandwitdh)builder.visit(rule);
        //Add it
        media.addBandwidth(bandwidth);
        //Return it
        return bandwidth;
    }

    @Override
    public Object visit(Rule$information_field rule) {
        //Create new session name
        InformationBuilder builder = new InformationBuilder();
        //Generate
        Information info = (Information) builder.visit(rule);
        //Set it
        media.setInformation(info);
        //Return it
        return info;
    }

    @Override
    public Object visit(Rule$attribute_field rule) {
        //Create new session name
        AttributeBuilder builder = new AttributeBuilder();
        //Generate
        Attribute attr = (Attribute) builder.visit(rule);
        //Set it
        media.addAttribute(attr);
        //Return it
        return attr;
    }
}
